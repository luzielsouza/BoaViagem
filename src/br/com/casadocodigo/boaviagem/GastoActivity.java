package br.com.casadocodigo.boaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class GastoActivity extends Activity {
	private int ano, mes, dia;
	private Button dataGasto;
	private Spinner categoria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gasto);

		Calendar rightNow = Calendar.getInstance();
		ano = rightNow.get(Calendar.YEAR);
		mes = rightNow.get(Calendar.MONTH);
		dia = rightNow.get(Calendar.DAY_OF_MONTH);
		dataGasto = (Button) findViewById(R.id.data);
		dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);
		/*
		 * criamos um novo ArrayAdapter e o atribuímos ao spinner decategorias
		 * 
		 * Para criar o ArrayAdapter utilizamos o método createFromResource,
		 * pas- sando o contexto atual, o identificador do array de opções que
		 * definimos no strings.xml e o id do layout que será utilizado para
		 * apresentar as opções. Mais uma vez nos beneficiamos do que está
		 * disponível na plataforma e utilizamos o layout
		 * android.R.layout.simple_spinner_item já definido.
		 */
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categoria_gasto,
				android.R.layout.simple_spinner_item);
		categoria = (Spinner) findViewById(R.id.categoria);
		categoria.setAdapter(adapter);
	}

	@SuppressWarnings("deprecation")
	public void selecionarData(View view) {
		/*
		 * invocado para criar uma caixa de diálogo pela primeira vez
		 */
		showDialog(view.getId());

	}

	/*
	 * o método onCreateDialog é chamado, passando o identicador informado,
	 * para que seja instanciado um novo DatePickerDialog.
	 */

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (R.id.data == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return null;
	}

	/*
	 * listener será responsável por tratar o resultado, ou seja, a data
	 * escolhida pelo usuário. Fazemos isso através da denição de uma classe
	 * anônima, que implementa OnDateSetListener, para o listener utilizado.
	 * Esta classe possui apenas um método que será invocado pelo próprio
	 * DatePickerDialog quando uma data for selecionada, que é o método
	 * onDateSet.
	 */

	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			/*
			 * Em nossa implementação, recuperamos os valores de ano, mês e dia
			 * informados e atualizamos o texto do botão para apresentar como
			 * resposta ao usuário.
			 */
			dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};
}