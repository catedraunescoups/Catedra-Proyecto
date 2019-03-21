import { Component  } from '@angular/core';
import { IonicPage, NavController, LoadingController, Loading } from 'ionic-angular';
import { RoomuPage } from '../roomu/roomu';
import { RoomPage } from '../room/room';
import { RestProvider } from '../../providers/rest/rest';
import { Storage } from '@ionic/storage';
import 'rxjs/add/operator/catch';
import {ToastController} from 'ionic-angular'



/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
@IonicPage()
@Component({
  
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
    //Declaracion de variables
    data = { nickname:" " ,contrasena:" "};
    loading: Loading;
    i=0;
    i1=0;
    credentials:any
    isLogged: boolean;
    responseData : any;
    usuarios:String[];

  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController,
    public proveedor:RestProvider, public storage:Storage, private toastCtrl: ToastController ) {
  }
 
  ionViewDidLoad() {
  }
//Metodo para extraer usuarios con respectivas contraseñas, realizando las comparaciones necesarias,
//para la asignacion de roles.
  getloginUsers(){
    //this.showLoading()
    this.presentLoadingCustom()
    //this.presentLoadingDefault()
    this.proveedor.getUsuarios()
       .subscribe(
        usuarios => {this.credentials = usuarios
                     this.credentials.map(item=>{
            if(item.nombre.replace(/\s+/g, '')==this.data.nickname.replace(/\s+/g, '') && 
            item.contrasena.replace(/\s+/g, '')==this.data.contrasena.replace(/\s+/g, '')){
              this.storage.set('usuario', this.data.nickname+" "+item.apellido);
              if (this.i == 0) { 
                this.presentToast()
                
                if(item.rol==3){
                this.navCtrl.setRoot(RoomPage, {
                  nickname: this.data.nickname+" "+item.apellido,
                  contrasena: this.data.contrasena
                 });
                }else{
                  this.navCtrl.setRoot(RoomuPage, {
                    nickname: this.data.nickname+" "+item.apellido,
                    contrasena: this.data.contrasena
                });
            }
          } else{
            this.presentToastE()
          }

          }
            
          })
        });      
}

//Metodo para verificar los usuarios que ingresan al sistema y muestra un mensaje de carge
presentLoadingCustom() {
  let loading = this.loadingCtrl.create({
    content: 'Espere por favor...',
    duration: 1000
  });
  loading.onDidDismiss(() => {
    //console.log('Dismissed loading');   
  });
  loading.present();
}
//Metodo para imprimir mensaje de usuario correcto 
presentToast() {
  const toast = this.toastCtrl.create({
    message: 'Usuario correcto..',
    duration: 3000,
    position: 'down'
  });
  toast.onDidDismiss(() => {
    //console.log('Dismissed toast');
  });
  toast.present();
}
//Metodo para imprimir mensaje de usuario incorrecto 
presentToastE() {
  this.i1=1;
  const toast = this.toastCtrl.create({
    message: 'Usuario incorrecto..',
    duration: 3000,
    position: 'down'
  });
  toast.onDidDismiss(() => {
   // console.log('Dismissed toast');
  });
  toast.present();
}


}
