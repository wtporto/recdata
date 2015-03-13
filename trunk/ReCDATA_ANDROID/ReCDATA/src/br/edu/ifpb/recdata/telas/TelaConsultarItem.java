package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Categoria;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.Regiao;
import br.edu.ifpb.recdata.servicos.BuscaItensServidorAsyncTask;
import br.edu.ifpb.recdata.servicos.PreencherSpinnerCategoriaAsyncTask;
import br.edu.ifpb.recdata.servicos.PreencherSpinnerRegiaoAsyncTask;
import br.edu.ifpb.recdata.util.Constantes;

public class TelaConsultarItem extends Activity implements OnClickListener {

	// Objetos para montar o Json
	private Regiao regiao;
	private Item item;
	private Categoria categoria;

	private List<Categoria> listaCategorias = new ArrayList<Categoria>();
	private List<Regiao> listaRegiao = new ArrayList<Regiao>();

	private Spinner categoriaSpinner;
	private Spinner regiaoSpinner;
	private EditText descricaoItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaactivity_tela_consultar);

		getView();
		carregarCategoriaSpinner();
		carregarRegiaoSpinner();
		final ImageButton busca = (ImageButton) findViewById(R.id.imageButton_buscar);
		busca.setOnClickListener(this);
	}

	public void getView() {

		this.categoriaSpinner = (Spinner) findViewById(R.id.Spinner_Seleciona_Categoria);
		this.regiaoSpinner = (Spinner) findViewById(R.id.Spinner_Regiao);
		this.descricaoItem = (EditText) findViewById(R.id.editText_descricaoItem);

	}

	private void carregarCategoriaSpinner() {

		PreencherSpinnerCategoriaAsyncTask preencherSpinner = new PreencherSpinnerCategoriaAsyncTask(
				this);
		categoriaSpinner = (Spinner) findViewById(R.id.Spinner_Seleciona_Categoria);

		try {
			listaCategorias = preencherSpinner.execute().get();
			List<String> categoriaAux;
			Log.i("ReCDATA - ", listaCategorias.toString());
			if (listaCategorias != null) {
				categoriaAux = new ArrayList<String>();
				categoriaAux.add("Selecione..");
				for (int i = 0; i < listaCategorias.size(); i++) {
					categoriaAux.add(listaCategorias.get(i).getDescricao());
				}

				this.ativarSpinnerCategoria(categoriaSpinner, categoriaAux);

			} else {
				Toast.makeText(TelaConsultarItem.this.getApplicationContext(),
						Constantes.ERROR_TIPOUSUARIO_NULL, Toast.LENGTH_SHORT)
						.show();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void carregarRegiaoSpinner() {

		PreencherSpinnerRegiaoAsyncTask preencherSpinner = new PreencherSpinnerRegiaoAsyncTask(
				this);
		regiaoSpinner = (Spinner) findViewById(R.id.Spinner_Regiao);

		try {
			listaRegiao = preencherSpinner.execute().get();
			List<String> regiaoAux;
			Log.i("ReCDATA - ", listaRegiao.toString());
			if (listaRegiao != null) {
				regiaoAux = new ArrayList<String>();
				regiaoAux.add("Selecione..");
				for (int i = 0; i < listaRegiao.size(); i++) {
					regiaoAux.add(listaRegiao.get(i).getDescricao());
				}

				this.ativarSpinnerRegiao(regiaoSpinner, regiaoAux);

			} else {
				Toast.makeText(TelaConsultarItem.this.getApplicationContext(),
						Constantes.ERROR_TIPOUSUARIO_NULL, Toast.LENGTH_SHORT)
						.show();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ativarSpinnerCategoria(Spinner generico,
			List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

		generico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if (arg2 == 1) {
					montarObjetoCategoria(1);
				}
				if (arg2 == 2) {
					montarObjetoCategoria(2);
				}
				if (arg2 == 3) {
					montarObjetoCategoria(3);
				}
				if (arg2 == 4) {
					montarObjetoCategoria(4);
				}
				if (arg2 == 5) {
					montarObjetoCategoria(5);
				}
				if (arg2 == 6) {
					montarObjetoCategoria(6);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	public void ativarSpinnerRegiao(Spinner generico,
			List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

		generico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if (arg2 == 1) {
					montarObjetoRegiao(1);
				}

				if (arg2 == 2) {
					montarObjetoRegiao(2);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	public void montarObjetoCategoria(int id) {

		categoria = new Categoria();
		categoria.setId(id);

	}

	public void montarObjetoRegiao(int id) {

		regiao = new Regiao();
		regiao.setId(id);
	}

	public void montarObjetoItem() {

		item = new Item();
		item.setDescricao(descricaoItem.getText().toString().trim());
		item.setCategoria(categoria);
		item.setRegiao(regiao);
		Log.i("ReCData - item ", item.toString());
	}

	public JSONObject montarObjetoJSON() {

		JSONObject itemJsonObject = new JSONObject();

		montarObjetoItem();

		try {

			itemJsonObject.put("descricao", item.getDescricao().trim());

			JSONObject categoriaJsonObject = new JSONObject();
			categoriaJsonObject.put("id", categoria.getId());
			itemJsonObject.put("categoria", categoriaJsonObject);

			JSONObject regiaoJsonObject = new JSONObject();
			regiaoJsonObject.put("id", regiao.getId());
			itemJsonObject.put("regiao", regiaoJsonObject);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return itemJsonObject;
	}

	@Override
	public void onClick(View v) {
		JSONObject jsonObject = montarObjetoJSON();
		Log.i("JsonItem - ", jsonObject.toString());
		BuscaItensServidorAsyncTask buscaItem = new BuscaItensServidorAsyncTask(
				this);
		buscaItem.execute(jsonObject);

	}

}