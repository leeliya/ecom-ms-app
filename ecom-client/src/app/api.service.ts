import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Bill, Customer, Product } from './models';

// Toutes les requetes passent par la gateway (routes dynamiques : nom du service en majuscules)
const GATEWAY = 'http://localhost:8888';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private http = inject(HttpClient);

  getCustomers(): Observable<Customer[]> {
    return this.http
      .get<any>(`${GATEWAY}/CUSTOMER-SERVICE/customers`)
      .pipe(map((r) => r._embedded.customers));
  }

  getProducts(): Observable<Product[]> {
    return this.http
      .get<any>(`${GATEWAY}/INVENTORY-SERVICE/products`)
      .pipe(map((r) => r._embedded.products));
  }

  getBill(id: number): Observable<Bill> {
    return this.http.get<Bill>(`${GATEWAY}/BILLING-SERVICE/bills/${id}`);
  }
}
