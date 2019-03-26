package com.leonardo.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leonardo.modelo.Pagamento;
import com.leonardo.modelo.Response;
import com.leonardo.repository.PagamentoRepository;


@RestController
@RequestMapping("/api/pagamento")
@CrossOrigin(origins = "*")
public class PagamentoController {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@GetMapping
	public List<Pagamento> listar() {
		return pagamentoRepository.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<Response<Pagamento>> create(HttpServletRequest request, @RequestBody Pagamento pagamento,
			BindingResult result) {
		Response<Pagamento> response = new Response<Pagamento>();
		try {
			validateCreatePagamento(pagamento, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
//			pagamento.setCliente("");
			pagamento.setData(new Date());
//			pagamento.setFormaPagamento("");
//			pagamento.setObservacao("");
//			pagamento.setProjeto("");
//			pagamento.setTipoDespesa("");
//			pagamento.setTipoReembolso("");
//			pagamento.setValor(BigDecimal.ZERO);
			
			Pagamento pagamentoPersisted = (Pagamento) pagamentoRepository.save(pagamento);
			response.setData(pagamentoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateCreatePagamento(Pagamento pagamento, BindingResult result) {
		
		if (pagamento.getCliente() == null ||
			pagamento.getFormaPagamento() == null ||
			pagamento.getObservacao()  == null ||
			pagamento.getProjeto() == null ||
			pagamento.getTipoDespesa() == null ||
			pagamento.getTipoReembolso() == null ||
			pagamento.getValor() == null) {
				result.addError(new ObjectError("Pagamento", "Campo obrigatório"));
			return;
		}
	}
	
	@PutMapping()
	public ResponseEntity<Response<Pagamento>> update(HttpServletRequest request, @RequestBody Pagamento pagamento,
			BindingResult result) {
		Response<Pagamento> response = new Response<Pagamento>();
		try {
			validateUpdatePagamento(pagamento, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Optional<Pagamento> pagamentoCurrentOptional = pagamentoRepository.findById(pagamento.getId());
			Pagamento pagamentoCurrent = pagamentoCurrentOptional.get();
			
			pagamento.setData(pagamentoCurrent.getData());
			
			Pagamento pagamentoPersistedo = (Pagamento) pagamentoRepository.save(pagamento);
			response.setData(pagamentoPersistedo);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdatePagamento(Pagamento pagamento, BindingResult result) {
		if (pagamento.getId() == null) {
			result.addError(new ObjectError("Pagamento", "Id não informado"));
			return;
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(Integer.parseInt(id));
		Pagamento pagamento = pagamentoOptional.get();
		if (pagamento == null) {
			response.getErrors().add("Registro não encontrado id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		pagamentoRepository.deleteById(Integer.parseInt(id));
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Pagamento>> findById(@PathVariable("id") String id) throws ParseException {
		Response<Pagamento> response = new Response<Pagamento>();
		Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(Integer.parseInt(id));
		Pagamento pagamento = pagamentoOptional.get();
		if (pagamento == null) {
			response.getErrors().add("Registro não encontrado id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(pagamento);
		return ResponseEntity.ok(response);
	}
	
}
