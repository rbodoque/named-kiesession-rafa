package org.drools.example.api.namedkiesession;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import droolsbook.bank.model.AccountInfo;
import droolsbook.cep.bank.model.Account;
import droolsbook.cep.bank.model.AccountUpdatedEvent;
import droolsbook.cep.bank.model.TransactionCreatedEvent;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/beans-internal.xml"})
public class NamedKieSessionExampleTest {
	
	//@Resource(name="environment-test-kmodule")
	@Autowired
	private KieModuleModel kbase;
	
//	@Autowired
//	private KieModule kmodule;
//	
	@Resource(name="environment-test-kmodule")
    private  KieModuleModel kieModuleModel;
	
	@Resource(name="kiePostProcessor")
    private  KModuleBeanFactoryPostProcessor kModuleBeanFactoryPostProcessor;
	
	@Resource(name="ksession1")
	@KSession("ksession1")
	StatefulKnowledgeSession kieSession;
	
	@Autowired
	 ApplicationContext context; 

	 StatelessKieSession kieS; 
	 KieBase kiebase; 

	   
	
	
	@Test
	public void testGo() {
		
		 Account ac= new Account();
	        ac.setStatus( Account.Status.ACTIVE);
	        ac.setNumber(new Long("123"));
	        
	        kieSession.insert(ac);
	        
	        AccountInfo acInfo=new AccountInfo();
	        acInfo.setNumber(new Long("123"));
	        
	        kieSession.insert(acInfo);
	        EntryPoint atmStream =  kieSession.getEntryPoint( "AccountStream" );
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
	        
	        atmStream =  kieSession.getEntryPoint( "TransactionStream" );
	        atmStream.insert(tCreate);
	        
	        kieSession.fireAllRules();
	        //"TransactionStream"
	       
    	System.out.println();
    }
}
