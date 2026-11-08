import { DatePipe } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { Bill } from '../models';

@Component({
  selector: 'app-bill-details',
  imports: [DatePipe],
  template: `
    @if (bill(); as b) {
      <h2>Facture n° {{ b.id }}</h2>
      <p><strong>Date :</strong> {{ b.billingDate | date: 'dd/MM/yyyy' }}</p>
      <p><strong>Client :</strong> {{ b.customer.name }} ({{ b.customer.email }})</p>
      <table>
        <thead>
          <tr><th>Produit</th><th>Prix unitaire</th><th>Quantite</th><th>Total</th></tr>
        </thead>
        <tbody>
          @for (i of b.productItems; track i.id) {
            <tr>
              <td>{{ i.product.name }}</td>
              <td>{{ i.unitPrice.toFixed(2) }}</td>
              <td>{{ i.quantity }}</td>
              <td>{{ (i.unitPrice * i.quantity).toFixed(2) }}</td>
            </tr>
          }
        </tbody>
      </table>
      <p><strong>Total : {{ total().toFixed(2) }}</strong></p>
    } @else if (error()) {
      <p class="error">{{ error() }}</p>
    }
  `,
})
export class BillDetails implements OnInit {
  private api = inject(ApiService);
  private route = inject(ActivatedRoute);
  bill = signal<Bill | null>(null);
  error = signal('');

  total() {
    return (this.bill()?.productItems ?? []).reduce((s, i) => s + i.unitPrice * i.quantity, 0);
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.api.getBill(id).subscribe({
      next: (b) => this.bill.set(b),
      error: () => this.error.set('Facture introuvable ou BILLING-SERVICE injoignable'),
    });
  }
}
