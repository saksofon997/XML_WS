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

  selectConversation(conv) {
    // get conv messages
  }

  sendMessage() {

  }

  getUserInfo() {

  }

  refresh(){

  }
}
