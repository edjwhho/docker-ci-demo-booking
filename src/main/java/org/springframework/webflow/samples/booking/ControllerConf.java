package org.springframework.webflow.samples.booking;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@ControllerAdvice
@SessionAttributes(types=ContainerConf.class)
public class ControllerConf {

	@ModelAttribute("containerconf")
	public ContainerConf getContainerConf() {

		String hostname = System.getenv("HOSTNAME");
		ContainerConf c = new ContainerConf();
		c.setHostName(hostname);

		return c;

	}

}
