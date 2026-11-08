import { Component, inject, OnInit, signal } from '@angular/core';
import { ApiService } from '../api.service';
import { Product } from '../models';

@Component({
  selector: 'app-products',
  template: `
    <h2>Produits</h2>
    @if (error()) {
      <p class="error">{{ error() }}</p>
    }
    <table>
      <thead>
        <tr><th>ID</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr>
      </thead>
      <tbody>
        @for (p of products(); track p.id) {
          <tr>
            <td>{{ p.id }}</td>
            <td>{{ p.name }}</td>
            <td>{{ p.price }}</td>
            <td>{{ p.quantity }}</td>
          </tr>
        }
      </tbody>
    </table>
  `,
})
export class Products implements OnInit {
  private api = inject(ApiService);
  products = signal<Product[]>([]);
  error = signal('');

  ngOnInit() {
    this.api.getProducts().subscribe({
      next: (data) => this.products.set(data),
      error: () => this.error.set('Impossible de joindre INVENTORY-SERVICE via la gateway'),
    });
  }
}
