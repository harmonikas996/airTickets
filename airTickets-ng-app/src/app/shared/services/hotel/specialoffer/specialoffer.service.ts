import { Specialoffer } from './../../../model/hotel/specialoffer.model';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SpecialofferService {

  private soUrl = 'http://localhost:8080/specialOffers';

  constructor(
    private http: HttpClient
  ) { }

  getSpecialoffer(): Observable<Specialoffer[]> {
    return this.http.get<Specialoffer[]>(this.soUrl + '/all');
  }

  getSpecialOfferById(id: number): Observable<Specialoffer> {
    return this.http.get<Specialoffer>(this.soUrl + '/' + id);
  }

  addSpecialoffer(specialoffer: Specialoffer): Observable<Object> {
    return this.http.post<Specialoffer>(this.soUrl + '/new', specialoffer, httpOptions);
  }

  updateSpecialoffer(specialoffer: Specialoffer): Observable<Object> {
    return this.http.put<Specialoffer>(this.soUrl + '/' + specialoffer.id + '/update', specialoffer, httpOptions);
  }

  removeSpecialoffer(specialoffer: Specialoffer | number): Observable<Specialoffer> {
    const id = typeof specialoffer === 'number' ? specialoffer : specialoffer.id;
    return this.http.delete<Specialoffer>(this.soUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted Specialoffer id=${id}`))
    );
  }


}
