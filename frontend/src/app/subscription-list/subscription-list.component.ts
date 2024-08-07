import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SubscriptionService } from '../subscription.service';
import { Subscription } from '../subscription';

@Component({
  selector: 'app-subscription-list',
  templateUrl: './subscription-list.component.html',
  styleUrls: ['./subscription-list.component.css']
})
export class SubscriptionListComponent implements OnInit {
  subscriptions: Subscription[] = [];
  filteredSubscriptions: Subscription[] = [];
  filterChannelName: string = '';
  filterChannelUrl: string = '';
  filterSubscribedDate: string = ''; // Date in 'YYYY-MM-DD' format

  constructor(
    private subscriptionService: SubscriptionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getSubscriptions();
  }

  getSubscriptions(): void {
    this.subscriptionService.getSubscriptions()
      .subscribe(subscriptions => {
        this.subscriptions = subscriptions;
        this.filteredSubscriptions = subscriptions;
      });
  }

  deleteSubscription(id: number): void {
    this.subscriptionService.deleteSubscription(id)
      .subscribe(() => this.getSubscriptions());
  }

  openAddSubscriptionForm(): void {
    this.router.navigate(['/add-subscription']);
  }

  openEditSubscriptionForm(id: number): void {
    this.router.navigate(['/edit-subscription', id]);
  }

  search(): void {
    const formattedDate = this.filterSubscribedDate ? new Date(this.filterSubscribedDate).toISOString().split('T')[0] : '';

    this.subscriptionService.searchSubscriptions(
      this.filterChannelName,
      this.filterChannelUrl,
      formattedDate
    ).subscribe((results: Subscription[]) => {
      this.filteredSubscriptions = results;
    });
  }
}
