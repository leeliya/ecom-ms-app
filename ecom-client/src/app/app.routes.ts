import { Routes } from '@angular/router';
import { BillDetails } from './bills/bill-details';
import { Customers } from './customers/customers';
import { Products } from './products/products';

export const routes: Routes = [
  { path: '', redirectTo: 'customers', pathMatch: 'full' },
  { path: 'customers', component: Customers },
  { path: 'products', component: Products },
  { path: 'bills/:id', component: BillDetails },
];
