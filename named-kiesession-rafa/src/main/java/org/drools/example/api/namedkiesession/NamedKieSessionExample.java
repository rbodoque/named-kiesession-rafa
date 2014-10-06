package org.drools.example.api.namedkiesession;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.UUID;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import droolsbook.bank.model.AccountInfo;
import droolsbook.cep.bank.model.Account;
import droolsbook.cep.bank.model.AccountUpdatedEvent;
import droolsbook.cep.bank.model.LostCardEvent;

public class NamedKieSessionExample {

    public void go(PrintStream out) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
       
        KieSession kSession = kContainer.newKieSession("ksession1");
        
        
//        TransactionCreatedEvent tCreate=new TransactionCreatedEvent(new BigDecimal(10),
//        	      new Long("123"), new Long("321"),UUID.randomUUID());
//        
        //kSession.setGlobal("out", out);
        
        Account ac= new Account();
        ac.setStatus( Account.Status.ACTIVE);
        ac.setNumber(new Long("123"));
        
        kSession.insert(ac);
        
        AccountInfo acInfo=new AccountInfo();
        acInfo.setNumber(new Long("123"));
        
        kSession.insert(acInfo);
        
        
        
        //"TransactionStream"
        SessionRunnable hilo = new SessionRunnable();
        hilo.setkSession(kSession);
        
        (new Thread(hilo)).start();
        
        
//        LostCardEvent perdida=new LostCardEvent(new Long("123"));
//        
//        atmStream = kSession.getEntryPoint( "LostCardStream" );
//        
//        atmStream.insert(perdida);
        kSession.fireAllRules();
        
        //kSession.insert(tCreate);
        
        
        
        
        
        
        
        //kSession.fireAllRules();
        
        
    }


    public static void main(String[] args) {
        new NamedKieSessionExample().go(System.out);
    }

}
