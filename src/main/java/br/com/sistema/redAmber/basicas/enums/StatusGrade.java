package br.com.sistema.redAmber.basicas.enums;

public enum StatusGrade {
	ATIVA {
		@Override
		public String toString() {
			return "Ativa";
		}
	},
	INATIVA {
		@Override
		public String toString() {
			return "Inativa";
		}
	},
	PENDENTE {
		@Override
		public String toString() {
			return "Pendente";
		}
	}
}