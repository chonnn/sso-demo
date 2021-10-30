import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-token',
  templateUrl: './token.component.html',
  styleUrls: ['./token.component.css']
})
export class TokenComponent implements OnInit {

  code:string = '';

  constructor(private activatedRoute:ActivatedRoute,
    private authService:AuthService) { 
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params) => {
      this.code = params['code'];
      this.saveToken(params['code']);
    });
  }

  saveToken(code:string){
    this.authService.callGetToken(code).subscribe(res=>{
      this.authService.saveToken(res.access_token);
    });
  }

}
