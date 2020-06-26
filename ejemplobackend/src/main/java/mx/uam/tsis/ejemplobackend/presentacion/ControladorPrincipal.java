package mx.uam.tsis.ejemplobackend.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorPrincipal {
	@GetMapping("/")
	public String index() {
		//log.info("jccjcjc");
		return "index";
	}
	
	@RequestMapping("/ejemplo")
	@ResponseBody
	public String ejemplo() {
		//log.info("jccjcjc");
		return "esto es un ejemplo";
	}

}
