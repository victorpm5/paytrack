package dev.paytrack.paytrack.deutschebank;


import android.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import dev.paytrack.paytrack.domain.Address;
import dev.paytrack.paytrack.domain.AddressResponse;
import dev.paytrack.paytrack.domain.CashAccount;
import dev.paytrack.paytrack.domain.CashAccountResponse;
import dev.paytrack.paytrack.domain.TokenResponse;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.domain.TransactionResponse;
import dev.paytrack.paytrack.domain.UserInfo;

public class ApiOperationsImpl implements ApiOperations  {

    private final String apiUrl = "https://simulator-api.db.com/gw/dbapi/v1";
    private static final String CLIENT_ID = "70b4ee41-2475-491c-bb15-33881819c5f4";
    private static final String CLIENT_SECRET_KEY = "Sd-meBHnjlXfh3UcJhSdyNOrQX8L8CzDxTGwH0A5hA2pavhn2vtGA-vhnbjTJ3yz05gTTqebs6qUugbUcXK9JQ";

    private final String accessToken = "patata"; //TODO delete -> get token by param or as property

    @Override
    public List<Address> getAddress() {

        AddressResponse resposta = null;

        try {

            String url = getUrlFromPath("/addresses");

            HttpEntity<String> requestEntity = getAuthorizationEntity();

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<AddressResponse> response = restTemplate
                    .exchange(url, HttpMethod.GET, requestEntity, AddressResponse.class);

            resposta = response.getBody();

        }catch(Exception e){
            return null;
        }

        return resposta.getAddressList();
    }

    @Override
    public List<CashAccount> getCashAccount() {

        CashAccountResponse resposta = null;

        try {

            String url = getUrlFromPath("/cashAccounts");

            HttpEntity<String> requestEntity = getAuthorizationEntity();

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<CashAccountResponse> response = restTemplate
                    .exchange(url, HttpMethod.GET, requestEntity, CashAccountResponse.class);

            resposta = response.getBody();

        }catch(Exception e){
            return null;
        }

        return resposta.getCashAccountList();
    }

    @Override
    public List<Transaction> getTransactions() {

        TransactionResponse resposta = null;

        try {

            String url = getUrlFromPath("/transactions");

            HttpEntity<String> requestEntity = getAuthorizationEntity();

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<TransactionResponse> response = restTemplate
                    .exchange(url, HttpMethod.GET, requestEntity, TransactionResponse.class);

            resposta = response.getBody();

        }catch(Exception e){
            return null;
        }

        return resposta.getTransactionList();
    }

    @Override
    public UserInfo getUserInfo() {

        UserInfo resposta = null;

        try {

            String url = getUrlFromPath("/userInfo");

            HttpEntity<String> requestEntity = getAuthorizationEntity();

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<UserInfo> response = restTemplate
                    .exchange(url, HttpMethod.GET, requestEntity, UserInfo.class);

            resposta = response.getBody();

        }catch(Exception e){
            return null;
        }

        return resposta;
    }

    @Override
    public String getUserToken(String code){

        String url = UriComponentsBuilder.fromUriString("https://simulator-api.db.com/gw/oidc")
                .path("/token")
                .queryParam("grant_type","authorization_code")
                .queryParam("code",code)
                .queryParam("redirect_uri","http://bienepaytrack.dev")
                .build()
        .toString();

        String authorization = CLIENT_ID.concat(":").concat(CLIENT_SECRET_KEY);

        byte[] data = authorization.getBytes();
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization","Basic:" + base64);

        HttpEntity<String> requestEntity = new HttpEntity<String>("Params", headers);

        RestTemplate restTemplate = new RestTemplate();

        TokenResponse resposta = null;

        try {

            HttpEntity<TokenResponse> response = restTemplate
                    .exchange(url, HttpMethod.GET, requestEntity, TokenResponse.class);

            resposta = response.getBody();

        }catch(Exception e){
            System.out.println(e.toString());
         }

        return null;
    }

    private String getUrlFromPath(String path){

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .path(path)
                .build()
                .toString();

        return url;
    }

    private HttpEntity<String> getAuthorizationEntity(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<String>("Params", headers);

        return requestEntity;
    }
}
