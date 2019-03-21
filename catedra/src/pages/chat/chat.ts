import { Component, ViewChild } from '@angular/core';
import { IonicPage, NavController, NavParams, Content } from 'ionic-angular';
import { RoomPage } from '../room/room';
import 'rxjs/add/operator/catch';
import * as firebase from 'Firebase';
import { Storage } from '@ionic/storage';
/**
 * Generated class for the ChatPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-chat',
  templateUrl: 'chat.html',
  
})

export class ChatPage {

  @ViewChild(Content) content: Content;

  data = { type:'', nickname:'', message:'' };
  chats = [];
  roomkey:string;
  nickname:string;
  user:string;
  offStatus:boolean = false;

  constructor(public navCtrl: NavController, public navParams: NavParams, public storage:Storage) {
    
  }
  ionViewDidLoad(){
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
      this.nickname = val;
      this.roomkey = this.navParams.get("key") as string;
      this.nickname = this.navParams.get("nickname") as string;
      this.data.type = 'message';
    
      let joinData = firebase.database().ref('chatrooms/'+this.roomkey+'/chats').push();
      joinData.set({
        type:'join',
        user:this.nickname,
        message:this.nickname+' in line.',
        sendDate:Date()
      });
      this.data.message = '';

    firebase.database().ref('chatrooms/'+this.roomkey+'/chats').on('value', resp => {
      this.chats = [];
      this.chats = snapshotToArray(resp);
      setTimeout(() => {
        if(this.offStatus === false) {
          this.content.scrollToBottom(300);
        }
      }, 1000);
    });
  });

  }

  sendMessage() {
    let newData = firebase.database().ref('chatrooms/'+this.roomkey+'/chats').push();
    this.storage.get('usuario').then((val) => {
    newData.set({
      type:this.data.type,
      user:val,
      message:this.data.message,
      sendDate:Date()
      
    });
  
   
    
    this.data.message = '';
  });
  }

  exitChat() {
    let exitData = firebase.database().ref('chatrooms/'+this.roomkey+'/chats').push();
    exitData.set({
      type:'exit',
      user:this.nickname,
      message:this.nickname+' of line.',
      sendDate:Date()
    });

    this.offStatus = true;
    this.navCtrl.setRoot(RoomPage, {
      nickname:this.nickname
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