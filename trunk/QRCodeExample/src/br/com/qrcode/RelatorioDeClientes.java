package br.com.qrcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDeClientes {
	public static void main(String[] args) throws JRException, SQLException {
		
		List<Item> itens = new ArrayList<Item>();
		
		Item item1 = new Item("Data show", 1);
		ByteArrayOutputStream out = QRCode.from(Integer.toString(item1.getIdItem()))
                .to(ImageType.PNG).stream();
		InputStream is = new ByteArrayInputStream(out.toByteArray());
		item1.setQrCode(is);
		itens.add(item1);
		
		Item item2 = new Item("Sala", 2);
		ByteArrayOutputStream out2 = QRCode.from(Integer.toString(item2.getIdItem()))
                .to(ImageType.PNG).stream();
		InputStream is2 = new ByteArrayInputStream(out2.toByteArray());
		item2.setQrCode(is2);
		itens.add(item2);

		JasperPrint print = JasperFillManager.fillReport(
				"relatorios/qrcode_itens.jasper", null,
				new JRBeanCollectionDataSource(itens));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print,
				"relatorios/itens.pdf");

		System.out.println("Relatório gerado.");
	}
}
