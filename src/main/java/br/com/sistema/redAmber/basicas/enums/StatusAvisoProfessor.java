package br.com.sistema.redAmber.basicas.enums;

public enum StatusAvisoProfessor {
	ENVIADO {
		@Override
		public String toString() {
			return "Enviado";
		}
	},
	RECEBIDO {
		@Override
		public String toString(){
			return "Recebido";
		}
	}
}