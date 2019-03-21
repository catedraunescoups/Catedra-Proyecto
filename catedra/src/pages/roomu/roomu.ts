import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ChatPage } from '../chat/chat';
import { AddroomPage } from '../addroom/addroom';
import * as firebase from 'Firebase';
import { Storage } from '@ionic/storage';

/**
 * Generated class for the RoomuPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-roomu',
  templateUrl: 'roomu.html',
})
export class RoomuPage {rooms = [];
  data = { nickname:" " ,password:" "};
  ref = firebase.database().ref('chatrooms/');

  constructor(public navCtrl: NavController, public navParams: NavParams, public storage:Storage) {
    this.ref.on('value', resp => {
      this.rooms = [];
      this.rooms = snapshotToArray(resp);
      
    });
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad RoomPage');
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
    });
  }
  
  addRoom() {
    this.navCtrl.push(AddroomPage);
  }

  joinRoom(key) {
    this.navCtrl.setRoot(ChatPage, {
      key:key,
      nickname:this.navParams.get("nickname")
    });
  }
  enterNickname() {
    this.navCtrl.setRoot(RoomuPage, {
      nickname: this.data.nickname,
      password: this.data.password
    });
  }
  
 
}

export const snapshotToArray = snapshot => {
    let returnArr = [];

    snapshot.forEach(childSnapshot => {
        let item = childSnapshot.val();
        item.key = childSnapshot.key;
        returnArr.push(item);
    });

    return returnArr;
};
