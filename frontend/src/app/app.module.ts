import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {Angular2FontawesomeModule} from "angular2-fontawesome";

import {
  AlertModule,
  BsDropdownModule,
  ButtonsModule,
  CollapseModule,
  ModalModule,
  PaginationModule,
  TabsModule,
  TooltipModule
} from 'ngx-bootstrap';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";

import {AppComponent} from './app.component';
import {MainComponent} from './MainModule/Components/main/main.component';
import {notFoundComponent} from './MainModule/Components/notFound/notFound.component';
import {RegistrationComponent} from "./MainModule/Components/registration/registration.component";
import {HeaderComponent} from "./MainModule/Components/header/header.component";
import {LoginModalComponent} from "./MainModule/Components/header/login/login.component";
import {EditCustomerModalComponent} from "./MainModule/Components/adminPanel/editCustomerModal/editCustomerModal.component";
import {EditSubscriptionModalComponent} from "./MainModule/Components/ownerAccountInfo/editSubscriptionModal/editSubscriptionModal.component";
import {EditOwnerModalComponent} from "./MainModule/Components/adminPanel/editOwnerModal/editOwnerModal.component";
import {ShoppingListComponent} from "./MainModule/Components/shoppingList/shoppingList.component";
import {AdminPanelComponent} from "./MainModule/Components/adminPanel/adminPanel.component";
import {ConfirmModalComponent} from "./MainModule/Components/confirmModal/confirmModal.component";
import {WalletModalComponent} from "./MainModule/Components/header/wallet/wallet.component";
import {OwnerAccountInfoComponent} from "./MainModule/Components/ownerAccountInfo/ownerAccountInfo.component";
import {CustomerAccountInfoComponent} from "./MainModule/Components/customerAccountInfo/customerAccountInfo.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TokenStorage} from "./services/token.storage";
import {Interceptor} from "./services/interceptor.service.";
//Guards
import {AdminPanelGuard} from "./MainModule/Components/adminPanel/adminPanel.guard";
import {RegistrationGuard} from "./MainModule/Components/registration/registration.guard";
import {AdminNavBarComponent} from "./MainModule/Components/adminPanel/adminNavBar/adminNavBar.component";
import {OwnersListComponent} from "./MainModule/Components/adminPanel/ownersList/ownersList.component";
import {AdminSideBarComponent} from "./MainModule/Components/adminPanel/adminSideBar/adminSideBar.component";

// определение маршрутов
const appRoutes: Routes =[
  { path: '', component: MainComponent},
  { path: 'registration', component: RegistrationComponent, canActivate: [RegistrationGuard]},
  { path: 'shoppingList', component: ShoppingListComponent},
  { path: 'ownerAccountInfo/:id', component: OwnerAccountInfoComponent},
  { path: 'customerAccountInfo/:id', component: CustomerAccountInfoComponent},
  { path: 'adminPanel', component: AdminPanelComponent, canActivate: [AdminPanelGuard]},
  { path: 'adminPanel/Owners', component: OwnersListComponent, canActivate: [AdminPanelGuard]},
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
    CustomerAccountInfoComponent,
    ConfirmModalComponent,
    AdminNavBarComponent,
    OwnersListComponent,
    AdminSideBarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Angular2FontawesomeModule,
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
    AdminPanelGuard,
    RegistrationGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [LoginModalComponent, WalletModalComponent, EditOwnerModalComponent, EditCustomerModalComponent, EditSubscriptionModalComponent] //Массив entryComponents используется для определения только тех компонентов,
  // которые не найдены в html и динамически создаются с помощью ComponentFactoryResolver.
  // Angular нуждается в этом подсказке, чтобы найти их и скомпилировать.
})
export class AppModule {}
