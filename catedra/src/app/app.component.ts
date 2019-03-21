import { Component, ViewChild  } from '@angular/core';
import { Platform,Nav, AlertController  } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import * as firebase from 'firebase';
import { HomePage } from '../pages/home/home';
import { InformacionPage } from '../pages/informacion/informacion';
import { NoticiasPage } from '../pages/noticias/noticias';
import { LoginPage } from '../pages/login/login';
import { App } from 'ionic-angular';
import { RoomPage } from '../pages/room/room';
const config = {
  apiKey: 'AIzaSyBJrltw00LZq0hJZCCUk6Pmqolm5mAWh94',
  authDomain: 'chatcatedra.firebaseapp.com',
  databaseURL: 'https://chatcatedra.firebaseio.com',
  projectId: 'chatcatedra',
  storageBucket: 'chatcatedra.appspot.com',
  messagingSenderId: "285757841175"
};

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  rootPage :any = LoginPage;
  rooms = [];
  data = { nickname:" " ,password:" "};
  roomkey:string;
  nickname:string;
  offStatus:boolean = false;
  pages: Array<{title: string, component: any, image: any}>;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, public alertCtrl: AlertController,public app: App) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
   
     
      this.pages = [
        { title: 'Eventos', component: HomePage, image:'../assets/imgs/calendario.png' },
        { title: 'Noticias', component: NoticiasPage, image:'../assets/imgs/furgoneta.png' },
        { title: 'Información', component: InformacionPage, image:'../assets/imgs/noticias.png' },
        { title: 'Chat', component: RoomPage, image:'../assets/imgs/conversacion.png' },
        { title: 'Cerrar sesión', component: LoginPage, image:'../assets/imgs/salida.png' },
        
      ];
    });
    firebase.initializeApp(config);

    platform.registerBackButtonAction(() => {
      // Catches the active view
      let nav = this.app.getActiveNavs()[0];
      let activeView = nav.getActive();                
      // Checks if can go back before show up the alert
      if(activeView.name === 'HomePage') {
          if (nav.canGoBack()){
              nav.pop();
          } else {
              const alert = this.alertCtrl.create({
                  title: 'Cerrar App',
                  message: 'Estas Seguro?',
                  buttons: [{
                      text: 'Cancelar',
                      role: 'cancel',
                      handler: () => {
                        this.nav.setRoot('HomePage');
                        
                      }
                  },{
                      text: 'Cerrar el App',
                      handler: () => {
                        platform.exitApp();
                      }
                  }]
              });
              alert.present();
          }
      }
  });
  }
  
  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    
    this.nav.setRoot(page.component);
  }
  ionViewDidLoad(){

  }
  
}

