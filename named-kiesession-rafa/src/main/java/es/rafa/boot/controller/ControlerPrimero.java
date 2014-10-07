package es.rafa.boot.controller;

import javax.annotation.Resource;

import org.kie.api.cdi.KSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import droolsbook.cep.bank.model.Account;

@RestController
public class ControlerPrimero {
	@Resource(name = "ksession1")
	@KSession("ksession1")
	StatefulKnowledgeSession kieSession;

	@RequestMapping("/primero")
	public String index() {
		Account ac = new Account();
		ac.setStatus(Account.Status.ACTIVE);
		ac.setNumber(new Long("123"));

		kieSession.insert(ac);
		return "Greetings from Spring Boot!";
	}
}
