package userAccount;
import java.util.ArrayList;
import java.util.List;

import bankAccount.BankAccount;
import domain.BankCode;
import domain.Person;

public class UserAccount {
	
	private BankCode bankCode;
	private Person accountOwner;
	private List<BankAccount> bankAccounts;
	
	public UserAccount(BankCode bc, Person accountOwner) {
		bankCode = bc;
		this.accountOwner = accountOwner;
		bankAccounts = new ArrayList<BankAccount>();
	}
	
	public boolean addBankAccount(BankAccount ba) {
		if(this.bankCode == ba.getBankCode()) {
			if(!bankAccounts.contains(ba)){
				bankAccounts.add(ba);
//				bankAccounts.sort(c); // TODO
			}
			return true;
		} else {
			return false;
		}
	}
	
}
