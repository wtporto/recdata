package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Categoria;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.Regiao;
import br.edu.ifpb.recdata.servicos.BuscaItensServidorAsyncTask;

public class TelaConsultarItem extends Activity implements OnClickListener {

	// Objetos para montar o Json
	private Regiao regiao;
	private Item item;
	private Categoria categoria;

	private List<String> listaCategorias = new ArrayList<String>();
	private List<String> listaRegiao = new ArrayList<String>();

	private Spinner categoriaSpinner;
	private Spinner regiaoSpinner;
	private EditText descricaoItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaactivity_tela_consultar);

		getView();
		carregarCategoriaRegiaoSpinner();
		

		final ImageButton busca = (ImageButton) findViewById(R.id.imageButton_buscar);
		busca.setOnClickListener(this);
	}

	public void getView() {

		this.categoriaSpinner = (Spinner) findViewById(R.id.Spinner_Seleciona_Categoria);
		this.regiaoSpinner = (Spinner) findViewById(R.id.Spinner_Regiao);
		this.descricaoItem = (EditText) findViewById(R.id.editText_descricaoItem);

	}

	private void carregarCategoriaRegiaoSpinner() {
		// Opções de Categorias
		listaCategorias.add("Sala");// 1
		listaCategorias.add("Data Show");// 2
		listaCategorias.add("Caixa de Som");// 3
		listaCategorias.add("Laboratório");// 4
		listaCategorias.add("Notebook");// 5

		categoriaSpinner = (Spinner) findViewById(R.id.Spinner_Seleciona_Categoria);
		this.ativarSpinnerCategoria(categoriaSpinner, listaCategorias);

		// Opções de Região
		listaRegiao.add("Área de Vivência");// 1
		listaRegiao.add("Centra de Aula");// 2

		regiaoSpinner = (Spinner) findViewById(R.id.Spinner_Regiao);
		this.ativarSpinnerRegiao(regiaoSpinner, listaRegiao);
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
				if (arg2 == 0) {
					montarObjetoCategoria(1);
				}

				if (arg2 == 1) {
					montarObjetoCategoria(2);
				}
				if (arg2 == 2) {
					montarObjetoCategoria(3);
				}
				if (arg2 == 3) {
					montarObjetoCategoria(4);
				}if (arg2 == 4) {
					montarObjetoCategoria(5);
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
				if (arg2 == 0) {
					montarObjetoRegiao(1);
				}
				if (arg2 == 1) {
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
			itemJsonObject.put("regiao", categoriaJsonObject);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return itemJsonObject;
	}

	@Override
	public void onClick(View v) {
		JSONObject jsonObject = montarObjetoJSON();
		BuscaItensServidorAsyncTask buscaItem= new BuscaItensServidorAsyncTask(this);
		buscaItem.execute(jsonObject);

	}

}