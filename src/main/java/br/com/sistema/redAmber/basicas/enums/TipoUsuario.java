package br.com.sistema.redAmber.basicas.enums;

public enum TipoUsuario {

	ALUNO {
		@Override
		public String toString() {
			return "Aluno";
		}
	},
	PROFESSOR {
		@Override
		public String toString() {
			return "Professor";
		}
	},
	FUNCIONARIO {
		@Override
		public String toString() {
			return "Funcionário";
		}
	}
}