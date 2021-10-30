import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenResponse } from 'src/app/interface/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenName = 'token';

  constructor(private router: Router,
    private http: HttpClient) {
  }

  callGetToken(code:string): Observable<TokenResponse> {
    // return this.http.get<TokenResponse>(`http://localhost:8080/auth/token?code=${code}`);

    const formData = new FormData();
    formData.append('grant_type', 'authorization_code');
    formData.append('scope', 'openid');
    formData.append('redirect_uri', 'http://127.0.0.1:4200/token');
    formData.append('client_id', 'web_client');
    formData.append('client_secret', 'web_client');
    formData.append('code', code);

    return this.http.post<any>('http://localhost:9000/oauth2/token', formData)
  }

  isLogin():boolean{
    if(localStorage.getItem(this.tokenName)){
      return true;
    }
    return false;
  }

  forwardToLogin() {
    //all variable should be config in environment file
    let authHost = 'http://127.0.0.1:9000'
    let redirectUrl = encodeURIComponent('http://127.0.0.1:4200/token');
    let nonce = 'yj5bxw1blye' //should be a ramdon string
    let loginUrl = `${authHost}/oauth2/authorize?client_id=web_client&redirect_uri=${redirectUrl}&scope=openid&response_type=code&response_mode=query&nonce=${nonce}`
    window.location.href = loginUrl;
  }

  saveToken(token: string) {
    localStorage.setItem(this.tokenName, token);
  }

  getToken():string | null{
    return localStorage.getItem(this.tokenName);
  }
}
