package laborant.report.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.unbescape.html.HtmlEscape;

import laborant.report.business.abstracts.ReportDefinitionService;
import laborant.report.entities.Laborant;
import laborant.report.entities.Patient;
import laborant.report.entities.ReportDefinition;

@RequestMapping("/api/reportdefinition") 
@Controller
public class ReportDefinitionsController {

	private ReportDefinitionService definitionService;
	
	@Autowired
	public ReportDefinitionsController(ReportDefinitionService definitionService) {
		super();
		this.definitionService = definitionService; 
	}
	
	@GetMapping("/reportdetailspage")
	public String getReportDetailsPage(Model model,ReportDefinition reportDefinition) {
		model.addAttribute("getalls",getAll());
		return "reportdetailspage";  
	} 
	 
	@GetMapping("reportdetailspage/createreport")
	public String showCreateForm(Model model) {
		ReportDefinition reportDefinition = new ReportDefinition();
		model.addAttribute("reportDefinition", reportDefinition); 
		model.addAttribute("listPatient", getAllPatient());
		model.addAttribute("listLaborant", getAllLaborant());
		model.addAttribute("pageTitle", "Rapor Ekleme Sayfası");
		return "createreport";
	}
	@PostMapping("reportdetailspage/createreport")
	public String saveReport(@Valid @ModelAttribute("reportDefinition") ReportDefinition reportDefinition, BindingResult bindingResult, Model model, RedirectAttributes rAtt) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("reportDefinition", reportDefinition); 
			model.addAttribute("listPatient", getAllPatient());
			model.addAttribute("listLaborant", getAllLaborant());
			return "createreport";
		}else {
			this.add(reportDefinition);
			rAtt.addFlashAttribute("message", "Kullanıcı başarıyla eklendi.");
			model.addAttribute("getalls",getAll());
			return "reportdetailspage";
		}
		
	}
	
	@GetMapping("/reportdetailspage/editreport/{id}")
	public String editReport(@PathVariable("id") Integer id, Model model,RedirectAttributes rAtt) {
		try {
			ReportDefinition reportDefinition = definitionService.getReportDefinition(id);
			model.addAttribute("listPatient", getAllPatient());
			model.addAttribute("listLaborant", getAllLaborant());
			model.addAttribute("reportDefinition", reportDefinition);
			model.addAttribute("pageTitle", "Rapor Düzenleme Sayfası ( ID :" + id + ")");
			return "createreport";
		} catch (Exception e) {
			rAtt.addAttribute("message", "Kullanıcı kaydedildi.");
			return "reportdetailspage";
		}
		
		
	}
	
	@GetMapping("/reportdetailspage/deletereport/{id}")
	public String deleteReport(@PathVariable("id") Integer id, Model model, ReportDefinition reportDefinition) {
		this.definitionService.delete(id);
		model.addAttribute("deleteMessage","Rapor Silindi (ID:" + id + ")");
		model.addAttribute("getalls",getAll());
		return "reportdetailspage";
	}
	
	public List<ReportDefinition> getAll(){
		return this.definitionService.getAll();
	} 
	
	public void add(ReportDefinition reportDefinition) {
		this.definitionService.add(reportDefinition); 
	}
	
	/*
	public void update(ReportDefinition reportDefinition) {
		this.definitionService.update(reportDefinition);
	} 
	*/
	
	public void delete(int reportDefinitionId) {
		this.definitionService.delete(reportDefinitionId);
	}
	
	public List<Laborant> getAllLaborant(){
		return this.definitionService.getAllLaborant();
	}
	
	public List<Patient> getAllPatient(){
		return this.definitionService.getAllPatient();
	}
	/*
	//Login form. 
    @RequestMapping("/login")
    public String login() {
        return "reportdetailspage";
    }

    //Login form with error. 
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
	*/
	   /** Error page. */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Error page. */
    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }
	
}
