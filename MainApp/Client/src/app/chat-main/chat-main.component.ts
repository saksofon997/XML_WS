import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { ChatService } from '../services/chat.service';

@Component({
  selector: 'app-chat-main',
  templateUrl: './chat-main.component.html',
  styleUrls: ['./chat-main.component.css']
})
export class ChatMainComponent implements OnInit {

  converesations: any;
  selectedUser: any;
  selectedConversation: any;
  newMessage: string;

  constructor(private userService: UserService,
    private chatService: ChatService) { }

  ngOnInit() {
    this.getConversations();
  }

  getConversations() {
    const userId = this.userService.getUser()?.id;
    this.chatService.getUserConversations(userId).subscribe(
      (data: any) => {
        this.converesations = data;
      },
      (error) => {
        alert(error);
      }
    );
  }

  selectConversation(conversation) {
    this.selectedConversation = conversation;
    this.selectedUser = conversation.user;
  }

  sendMessage() {
    if (!this.selectedConversation || !this.selectedUser.id || !this.newMessage) {
      return;
    }
    var message = {
      conversation_id: this.selectedConversation.id,
      sender_id: this.userService.getUser().id,
      receiver_id: this.selectedUser.id,
      text: this.newMessage,
      timestamp: (new Date()).getTime() / 1000,
    }
    console.log(message);


    this.chatService.sendMessage(message).subscribe(
      (data: any) => {
        this.selectedConversation = undefined;
        this.selectedConversation = this.selectedConversation;
        this.newMessage = "";
      },
      (error) => {
        alert(error);
      }
    );
  }

  getUserInfo() {

  }

  refresh() {

  }
}
