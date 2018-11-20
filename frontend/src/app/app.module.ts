import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { ReactiveFormsModule } from '@angular/forms';

import {
  CollapseModule, AlertModule, ButtonsModule,
  TooltipModule, BsDropdownModule, ModalModule,
  TabsModule, PaginationModule
} from 'ngx-bootstrap';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { Ng4LoadingSpinnerModule } from "ng4-loading-spinner";

import { AppComponent } from './app.component';
import { MainComponent } from './MainModule/Components/main/main.component';
import { notFoundComponent } from './MainModule/Components/notFound/notFound.component';
import { RegistrationComponent } from "./MainModule/Components/registration/registration.component";
import { HeaderComponent } from "./MainModule/Components/header/header.component";
import { LoginModalComponent } from "./MainModule/Components/login/login.component";
import { EditCustomerModalComponent } from "./MainModule/Components/editCustomerModal/editCustomerModal.component";
import { EditSubscriptionModalComponent } from "./MainModule/Components/editSubscriptionModal/editSubscriptionModal.component";
import { EditOwnerModalComponent } from "./MainModule/Components/editOwnerModal/editOwnerModal.component";
import { ShoppingListComponent } from "./MainModule/Components/shoppingList/shoppingList.component";
import { AdminPanelComponent } from "./MainModule/Components/adminPanel/adminPanel.component";
import { WalletModalComponent } from "./MainModule/Components/wallet/wallet.component";
import { OwnerAccountInfoComponent } from "./MainModule/Components/ownerAccountInfo/ownerAccountInfo.component";
import { CustomerAccountInfoComponent } from "./MainModule/Components/customerAccountInfo/customerAccountInfo.component";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TokenStorage } from "./services/token.storage";
import { Interceptor } from "./services/interceptor.service.";

//Guards
import { AdminPanelGuard } from "./MainModule/Components/adminPanel/adminPanel.guard";

// определение маршрутов
const appRoutes: Routes =[
  { path: '', component: MainComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: 'shoppingList', component: ShoppingListComponent},
  { path: 'ownerAccountInfo/:id', component: OwnerAccountInfoComponent},
  { path: 'customerAccountInfo/:id', component: CustomerAccountInfoComponent},
  { path: 'adminPanel', component: AdminPanelComponent, canActivate: [AdminPanelGuard]},
  { path: 'login', component: LoginModalComponent},
  { path: '**', component: notFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    notFoundComponent,
    RegistrationComponent,
    HeaderComponent,
    LoginModalComponent,
    EditOwnerModalComponent,
    EditSubscriptionModalComponent,
    EditCustomerModalComponent,
    ShoppingListComponent,
    AdminPanelComponent,
    WalletModalComponent,
    OwnerAccountInfoComponent,
    CustomerAccountInfoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AngularFontAwesomeModule,
    CollapseModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    AlertModule.forRoot(),
    ModalModule.forRoot(),
    ButtonsModule.forRoot(),
    Ng4LoadingSpinnerModule.forRoot(),
    TabsModule.forRoot(),
    PaginationModule.forRoot(),
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    TokenStorage,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    AdminPanelGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [LoginModalComponent, WalletModalComponent, EditOwnerModalComponent, EditCustomerModalComponent, EditSubscriptionModalComponent] //Массив entryComponents используется для определения только тех компонентов,
  // которые не найдены в html и динамически создаются с помощью ComponentFactoryResolver.
  // Angular нуждается в этом подсказке, чтобы найти их и скомпилировать.
})
export class AppModule {}
