package com.augustowebd.cursomc.domain.enuns;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		
		if (null == codigo) {
			return null;
		}
		
		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if (codigo.equals(ep.getCodigo())) {
				return ep;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
