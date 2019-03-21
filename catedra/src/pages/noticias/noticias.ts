import { Component } from '@angular/core';
import {  NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { ChatPage } from '../chat/chat';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { Storage } from '@ionic/storage';
/**
 * Generated class for the NoticiasPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-noticias',
  templateUrl: 'noticias.html',
})
export class NoticiasPage {

  noticias: string[];
  errorMessage: string;

  constructor(
    public navCtrl: NavController,public proveedor:RestProvider, 
    public http: Http,public navParams: NavParams, public storage:Storage ) {
    
    
  }
  ionViewDidLoad() {
    this.getNoticias();
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
    });
    
  }
  getNoticias() {
    this.proveedor.getNoticias()
       .subscribe(
         noticias => this.noticias = noticias);
  }
  joinRoom(key) {
    this.navCtrl.setRoot(ChatPage, {
      key:key,
      nickname:this.navParams.get("nickname")
    });
  }
  


}
