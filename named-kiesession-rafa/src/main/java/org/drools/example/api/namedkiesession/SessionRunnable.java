package org.drools.example.api.namedkiesession;

import java.math.BigDecimal;
import java.util.UUID;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import droolsbook.cep.bank.model.AccountUpdatedEvent;
import droolsbook.cep.bank.model.TransactionCreatedEvent;

public class SessionRunnable implements Runnable{
	KieSession kSession;
	
	public KieSession getkSession() {
		return kSession;
	}

	public void setkSession(KieSession kSession) {
		this.kSession = kSession;
	}

	public void run() {
//		KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//       
//         = kContainer.newKieSession("ksession1");
        
        EntryPoint atmStream = kSession.getEntryPoint( "AccountStream" );
        AccountUpdatedEvent update= new AccountUpdatedEvent(
        	      new Long("123"),new  BigDecimal(10), new BigDecimal(10),
        	      UUID.randomUUID());
        
        atmStream.insert(update);
        
        update= new AccountUpdatedEvent(
      	      new Long("123"),new  BigDecimal(15), new BigDecimal(15),
      	      UUID.randomUUID());
        
        atmStream.insert(update);
        
        TransactionCreatedEvent tCreate=new TransactionCreatedEvent(new BigDecimal(10),
      	      new Long("123"), new Long("321"),UUID.randomUUID());
        
        atmStream = kSession.getEntryPoint( "TransactionStream" );
        atmStream.insert(tCreate);
        //kSession.fireAllRules();
        
        
    }
}
