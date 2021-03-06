package co.edu.uniandes.ecos.statusquo.web.criteria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PacienteCriteria {
	
	private Long identificacion;
	
	private Date fechaInicio;
	
	private Date fechaFin;

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getFechaInicioString() {
		if (fechaInicio == null) return null;
		return new SimpleDateFormat("yyyyMMdd").format(fechaInicio);
	}
	
	public String getFechaFinString() {
		if (fechaFin == null) return null;
		return new SimpleDateFormat("yyyyMMdd").format(fechaFin);
	}

}
