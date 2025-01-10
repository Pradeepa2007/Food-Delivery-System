package com.java.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creditcards")
public class CreditCardInfo {
	
	    @Id
	    private String id;
	    private String cardNumber;
	    private String cardHolderName;
	    private String expiryMonth;
	    private String expiryYear;
	    private String securityCode;

}
