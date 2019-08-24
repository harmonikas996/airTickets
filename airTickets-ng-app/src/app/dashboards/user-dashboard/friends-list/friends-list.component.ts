import { UserService } from 'src/app/shared/services/user/user.service';
import { FriendshipService } from './../../../shared/services/user/friendship.service';
import { Friendship } from './../../../shared/model/user/friendship';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/shared/model/user/user.model';

@Component({
  selector: 'app-friends-list',
  templateUrl: './friends-list.component.html',
  styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {

  friends: Friendship[];
  friend: Friendship;

  friendObj: Friendship = new Friendship();

  friendRequests: Friendship[];
  friendRequest: Friendship;

  addFriendForm: FormGroup;

  users: User[];
  user: User;
  logUser: User;

  constructor(
    private friendshipService: FriendshipService,
    private userService: UserService,
    private formBuilder: FormBuilder,

    ) { }

  ngOnInit() {

    this.addFriendForm = this.formBuilder.group({
      name: [''],
    });

    this.getFriends();
    this.getFriendRequests();
    this.getUser();
  }

  getUser(): void {
    this.userService.getUserById().subscribe(
      logUser => this.logUser = logUser,
    );
  }

  getFriends(): void {
    this.friendshipService.getFriends().subscribe(
      friends => this.friends = friends,
    );
  }

  getFriendRequests(): void {
    this.friendshipService.getFriendRequests().subscribe(
      friendRequests => this.friendRequests = friendRequests,
    );
  }

  onRemove(friend: Friendship): void {
    this.friends = this.friends.filter(v => v !== friend);
    this.friendshipService.removeFriendship(friend.id).subscribe(friend => this.friend = friend);
  }

  accept(friend: Friendship): void {
    this.friendshipService.accept(friend).subscribe(Object => this.friend = friend);
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
    users => this.users = users,
  );
 }

 addFriendSubmit(user: User) {
  this.friendObj.confirmed = false;
  this.friendObj.initier = this.logUser.id;
  this.friendObj.confirmer = user.id;

  console.log(this.friendObj);

  this.friendshipService.addFriend(this.friendObj).subscribe((response) => {
    console.log("Response is: ", response);
  });

}




}
