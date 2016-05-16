package br.com.sistema.redAmber.basicas.enums;

public enum StatusReserva {
	PENDENTE {
		@Override
		public String toString() {
			return "Pendente";
		}
	},
	APROVADA {
		@Override
		public String toString() {
			return "Aprovada";
		}
	},
	NEGADA {
		@Override
		public String toString() {
			return "Negada";
		}
	}
}