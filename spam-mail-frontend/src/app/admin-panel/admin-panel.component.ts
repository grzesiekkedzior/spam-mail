import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Time} from '@angular/common';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(private http: HttpClient, private toastr: ToastrService) {
  }

  to: string;
  subject: string;
  content: string;
  sentAt: string; // hh:mm:ss
  file: File;


  selectedFile: ImageSnippet;

  onFileChanged(event) {
    this.file = event.target.files[0];
    this.toastr.success('Zdjęcie dodane poprawnie', 'Dodano zdjęcie');
  }

  onUpload() {
    this.sentAt = this.sentAt.split('.').join('');
    const uploadData = new FormData();
    if (this.file){
    uploadData.append('file', this.file, this.file.name);
    }else{
      uploadData.append('file', null);
    }
    uploadData.append('to', this.to);
    uploadData.append('subject', this.subject);
    uploadData.append('content', this.content);
    uploadData.append('sentAt', this.sentAt);
    console.log((uploadData));
    // todo change url (typicaly should be set in enviroments)
    this.http.post('http://localhost:8080/api/emails',
      uploadData
    ).subscribe(
      res => {    this.toastr.success('Email zostanie wysłany o wskazanej godzinie', 'Sukces');
      }
    );

  }

  ngOnInit(): void {
  }

  // processFile(imageInput: any) {
  //   const file: File = imageInput.files[0];
  //   const reader = new FileReader();
  //   reader.readAsDataURL(file);
  // }

}

class ImageSnippet {
  constructor(public src: string, public file: File) {
  }
}

export interface EmailRequestDto {
  to: string;
  subject: string;
  content: string;
  sentAt: Time;
  files: Array<File>;
}
