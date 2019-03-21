 import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import {HttpModule} from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { RestProvider } from '../providers/rest/rest';
import { InformacionPage } from '../pages/informacion/informacion';
import { NoticiasPage } from '../pages/noticias/noticias';
import { LoginPage } from '../pages/login/login';
import { ChatPage } from '../pages/chat/chat';
import { RoomPage } from '../pages/room/room';
import { AddroomPage } from '../pages/addroom/addroom';
import { IonicStorageModule } from '@ionic/storage';
import { RoomuPage } from '../pages/roomu/roomu';
import { IonicImageViewerModule } from 'ionic-img-viewer';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    InformacionPage,
    NoticiasPage,
    LoginPage,
    RoomPage,
    RoomuPage,
    AddroomPage,
    ChatPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    IonicImageViewerModule,
    IonicStorageModule.forRoot(),
    IonicModule.forRoot(MyApp),

  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    InformacionPage,
    NoticiasPage,
    LoginPage,
    ChatPage,
    RoomPage,
    RoomuPage,
    AddroomPage,
    ChatPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    RestProvider
  ]
})
export class AppModule {}
