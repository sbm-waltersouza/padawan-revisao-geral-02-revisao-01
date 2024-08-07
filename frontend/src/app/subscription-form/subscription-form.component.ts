import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SubscriptionService } from '../subscription.service';
import { Subscription } from '../subscription';

@Component({
  selector: 'app-subscription-form',
  templateUrl: './subscription-form.component.html',
  styleUrls: ['./subscription-form.component.css']
})
export class SubscriptionFormComponent implements OnInit {
  subscription: Subscription = new Subscription();
  id?: number;

  constructor(
    private subscriptionService: SubscriptionService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id') ? +this.route.snapshot.paramMap.get('id')! : undefined;
    if (this.id) {
      this.subscriptionService.getSubscriptionById(this.id).subscribe(subscription => {
        this.subscription = subscription;
      });
    }
  }

  onSubmit(): void {
    if (this.id) {
      this.subscriptionService.updateSubscription(this.id, this.subscription)
        .subscribe(() => this.router.navigate(['/subscriptions']));
    } else {
      this.subscriptionService.addSubscription(this.subscription)
        .subscribe(() => this.router.navigate(['/subscriptions']));
    }
  }

  cancel(): void {
    this.router.navigate(['/subscriptions']);
  }
}
