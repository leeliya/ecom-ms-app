import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ApiService } from '../api.service';
import { Customer } from '../models';

@Component({
  selector: 'app-customers',
  imports: [RouterLink],
  template: `
    <h2>Clients</h2>
    @if (error()) {
      <p class="error">{{ error() }}</p>
    }
    <table>
      <thead>
        <tr><th>ID</th><th>Nom</th><th>Email</th><th>Facture</th></tr>
      </thead>
      <tbody>
        @for (c of customers(); track c.id) {
          <tr>
            <td>{{ c.id }}</td>
            <td>{{ c.name }}</td>
            <td>{{ c.email }}</td>
            <td><a [routerLink]="['/bills', c.id]">Voir la facture</a></td>
          </tr>
        }
      </tbody>
    </table>
  `,
})
export class Customers implements OnInit {
  private api = inject(ApiService);
  customers = signal<Customer[]>([]);
  error = signal('');

  ngOnInit() {
    this.api.getCustomers().subscribe({
      next: (data) => this.customers.set(data),
      error: () => this.error.set('Impossible de joindre CUSTOMER-SERVICE via la gateway'),
    });
  }
}
