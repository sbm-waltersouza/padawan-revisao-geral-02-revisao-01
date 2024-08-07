import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from './subscription';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private baseUrl = 'http://localhost:8080/api/subscriptions';

  constructor(private http: HttpClient) {}

  getSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.baseUrl}`);
  }

  getSubscriptionById(id: number): Observable<Subscription> {
    return this.http.get<Subscription>(`${this.baseUrl}/${id}`);
  }

  addSubscription(subscription: Subscription): Observable<Subscription> {
    return this.http.post<Subscription>(`${this.baseUrl}`, subscription);
  }

  updateSubscription(id: number, subscription: Subscription): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}`, subscription);
  }

  deleteSubscription(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }


  searchSubscriptions(channelName?: string, channelUrl?: string, subscribedDate?: string): Observable<Subscription[]> {
    let params = new HttpParams();

    if (channelName) {
      params = params.set('channelName', channelName);
    }
    if (channelUrl) {
      params = params.set('channelUrl', channelUrl);
    }
    if (subscribedDate) {
      params = params.set('subscribedDate', subscribedDate);
    }

    return this.http.get<Subscription[]>(`${this.baseUrl}/search`, { params });
  }

  
}
