package com.payfort.sdk;

import com.payfort.sdk.hash.HashingFunction;
import static com.payfort.sdk.hash.HashingFunctions.SHA_256;

/**
 * To hold the FORT account security credentials
 * -   accessCode
 * -   merchantIdentifier
 * -   shaRequestPhrase
 * -   shaResponsePhrase
 */
public class FortAccount {

	private String accessCode;
	private String merchantIdentifier;
	private String shaRequestPhrase;
	private String shaResponsePhrase;
	private HashingFunction hashingFunction;
	
	/**
	 * with default hashing as SHA_256
	 * 
	 * @param accessCode
	 * @param merchantIdentifier
	 * @param shaRequestPhrase
	 * @param shaResponsePhrase
	 */
	public FortAccount(String accessCode, String merchantIdentifier, String shaRequestPhrase, String shaResponsePhrase) {
		this.accessCode = accessCode; 
		this.merchantIdentifier = merchantIdentifier;
		this.shaRequestPhrase = shaRequestPhrase;
		this.shaResponsePhrase = shaResponsePhrase;
		this. hashingFunction = SHA_256.hashingFunction();
	}
	
	public FortAccount(String accessCode, String merchantIdentifier, String shaRequestPhrase, String shaResponsePhrase, HashingFunction hashingFunction) {
		this.accessCode = accessCode; 
		this.merchantIdentifier = merchantIdentifier;
		this.shaRequestPhrase = shaRequestPhrase;
		this.shaResponsePhrase = shaResponsePhrase;
		this. hashingFunction = hashingFunction;
	}
	
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getMerchantIdentifier() {
		return merchantIdentifier;
	}
	public void setMerchantIdentifier(String merchantIdentifier) {
		this.merchantIdentifier = merchantIdentifier;
	}
	public String getShaRequestPhrase() {
		return shaRequestPhrase;
	}
	public void setShaRequestPhrase(String shaRequestPhrase) {
		this.shaRequestPhrase = shaRequestPhrase;
	}
	public String getShaResponsePhrase() {
		return shaResponsePhrase;
	}
	public void setShaResponsePhrase(String shaResponsePhrase) {
		this.shaResponsePhrase = shaResponsePhrase;
	}
	public HashingFunction getHashingFunction() {
		return hashingFunction;
	}
	
	 /**
     * Used to override hashing logic
     * Your Implementation must be thread safe
     */
	public void setHashingFunction(HashingFunction hashingFunction) {
		this.hashingFunction = hashingFunction;
	}	
}
