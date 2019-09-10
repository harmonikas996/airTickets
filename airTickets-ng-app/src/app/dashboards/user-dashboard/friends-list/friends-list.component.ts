import { UserService } from 'src/app/shared/services/user/user.service';
import { FriendshipService } from './../../../shared/services/user/friendship.service';
import { Friendship } from './../../../shared/model/user/friendship';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/shared/model/user/user.model';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-friends-list',
  templateUrl: './friends-list.component.html',
  styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {

  friends = [];
  friend: Friendship;

  friendObj: Friendship = new Friendship();
  acceptFriendShip: Friendship = new Friendship();

  friendRequests = [];
  friendRequest: Friendship;

  addFriendForm: FormGroup;

  users  = [];
  user: User;
  logUser: User;

  // All friedns

  allFriends = [];
  allFriendRequest = [];
  userLog: any;
  idMe: number;
  idUser: number;

  constructor(
    private friendshipService: FriendshipService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private tokenStorageService: TokenStorageService

    ) { }

  ngOnInit() {

    this.addFriendForm = this.formBuilder.group({
      name: [''],
    });

    this.getFriends();
    this.getFriendRequests();
    this.getUser();

    this.userLog = this.tokenStorageService.getUserId();
  }

  getUser(): void {
    this.userService.getUserById().subscribe(
      logUser => this.logUser = logUser,
    );
  }

  getFriends(): void {
    this.friendshipService.getFriends().subscribe(

      friends => {
        this.allFriends = [];
        for ( let f of friends) {

          if (f.confirmer == this.userLog) {
            this.idMe = f.initier;
          } else {
            this.idMe = f.confirmer;
          }

          this.userService.getUserByIdParam(this.idMe).subscribe(

            friendUser => {
              console.log(friendUser);
              this.allFriends.push({
                idFriendShip: f.id,
                firstName: friendUser.firstName,
                lastName: friendUser.lastName,
                city: friendUser.city,
                id: friendUser.id
              });

          });

        }

      }
    );
  }

  getFriendRequests(): void {
    this.friendshipService.getFriendRequests().subscribe(
      friendRequests => {

       for ( let f of friendRequests) {

        this.userService.getUserByIdParam(f.initier).subscribe(

          friendUser => {

            this.allFriendRequest.push({
              idFriendShip: f.id,
              confirmerReq: f.confirmer,
              initierReq: f.initier,
              confirmedReq: f.confirmed,
              firstName: friendUser.firstName,
              lastName: friendUser.lastName,
              city: friendUser.city,
              id: friendUser.id
            });

        });

      }

      }
    );
  }

  onRemove(friend: any, i: number): void {

    this.friends = this.friends.filter(v => v !== friend);
    this.friendshipService.removeFriendship(friend.idFriendShip).subscribe(friend => this.friend = friend);

    this.allFriends.splice(i, 1);

  }

  onRemoveFriednRequest(friend, i: number) {

    this.friends = this.friends.filter(v => v !== friend);
    this.friendshipService.removeFriendship(friend.idFriendShip).subscribe(friend => this.friend = friend);

    this.allFriendRequest.splice(i, 1);
  }

  accept(friend, i: number): void {

    this.acceptFriendShip.id = friend.idFriendShip;
    this.acceptFriendShip.confirmer = friend.confirmerReq;
    this.acceptFriendShip.initier = friend.initierReq;
    this.acceptFriendShip.confirmed = true;

    // provera prilikom search-a da ne ispisuje trenutnog user-a

    // provera da ne moze da doda nekoga ko se vec nalazi u all friends
    this.friendshipService.accept(this.acceptFriendShip).subscribe(Object => this.friend = friend);

    this.allFriendRequest.splice(i, 1);


    this.allFriends.push({
      idFriendShip: friend.idFriendShip,
      firstName: friend.firstName,
      lastName: friend.lastName,
      city: friend.city,
      id: friend.id
    });

 }


 onSubmit(){
  const text = this.addFriendForm.value['name'];
  const splitted = text.split(" ");

  let name1 = splitted[0];
  let name2 = splitted[1];

  if (name2 == undefined) {
    name2 = '';
  }

  const nameRes = name1 + "_" + name2;
  console.log(nameRes);

  this.userService.getUserSearch(nameRes).subscribe(

    users =>  {

      this.users = users.filter(user => {

        return !(user.id === +sessionStorage.getItem('UserId') ||
        this.allFriends.find(friend => friend.id === user.id) ||
        this.allFriendRequest.find(friend => friend.id === user.id));


      });

    }
  );
 }

 addFriendSubmit(user: User) {
  this.friendObj.confirmed = false;
  this.friendObj.initier = this.logUser.id;
  this.friendObj.confirmer = user.id;



  this.friendshipService.addFriend(this.friendObj).subscribe((response) => {
    console.log("Response is: ", response);
  });

  for(let i = 0; i < this.users.length; i++) {

    if( this.users[i].email == user.email) {
      this.users.splice(i,1);
    }
  }


}




}
