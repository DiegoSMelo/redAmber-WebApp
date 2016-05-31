package br.com.sistema.redAmber.basicas.enums;

public enum TipoFuncionario {
	C {
		@Override
		public String toString() {
			return "Coordenador";
		}
	},
	S {
		@Override
		public String toString() {
			return "Secretário";
		}
	}
}