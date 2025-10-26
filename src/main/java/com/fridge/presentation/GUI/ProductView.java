package com.fridge.presentation.GUI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.text.SimpleDateFormat;

import com.fridge.domain.entities.Products;
import com.fridge.domain.usecases.product.IDeleteProducts;
import com.fridge.domain.usecases.product.IGetProducts;
import com.fridge.domain.usecases.product.IPostProducts;
import com.fridge.domain.usecases.product.IPutProducts;

public class ProductView {
    private Table table;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final List<Control> editors = new ArrayList<Control>();

    private IGetProducts getProducts;
	private IPostProducts postProducts;
	private IPutProducts putProducts;
	private IDeleteProducts deleteProducts;
	
    public ProductView(Display display, IGetProducts getProducts, IPostProducts postProducts, 
			IPutProducts putProducts, IDeleteProducts deleteProducts) {
    	this.getProducts = getProducts;
    	this.postProducts = postProducts;
    	this.putProducts = putProducts;
    	this.deleteProducts = deleteProducts;
    	
        Shell shell = new Shell(display);
        shell.setText("Inventory System");
        shell.setSize(600, 400);
        shell.setLayout(new FillLayout());

        // Menu bar
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);

        MenuItem productMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        productMenuItem.setText("&Products");

        Menu productMenu = new Menu(shell, SWT.DROP_DOWN);
        productMenuItem.setMenu(productMenu);

        MenuItem openProducts = new MenuItem(productMenu, SWT.PUSH);
        openProducts.setText("Open Products");
        openProducts.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                openProductsScreen(shell);
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }

    private void openProductsScreen(Shell parent) {
        Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setText("Products");
        dialog.setSize(600, 400);
        dialog.setLayout(new GridLayout(1, false));

        Label title = new Label(dialog, SWT.NONE);
        title.setText("Products");
        title.setFont(parent.getDisplay().getSystemFont());

        Button addBtn = new Button(dialog, SWT.PUSH);
        addBtn.setText("Add Product");

        table = new Table(dialog, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        String[] titles = {"ID", "Name", "Price", "Expiry Date", "Actions"};
        for (String t : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(t);
            column.setWidth(120);

        }

        addBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                openProductForm(dialog, null);
            }
        });

        refreshTable(dialog);
        dialog.open();
    }
    
    private void refreshTable(Shell parent) {
    	for (Control c : editors) {
            if (!c.isDisposed()) c.dispose();
        }
    	System.out.println("Refreshing product table...");
    	editors.clear();
        table.removeAll();
        List<Products> products = this.getProducts.executeSync();
        for (Products product : products) {
        	TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(product.getId()));
            item.setText(1, product.getName());
            item.setText(2, String.format("%.2f", product.getProductInventory().getPrice()));
            String dateStr = product.getExpiryDate() != null ? 
            		dateFormat.format(product.getExpiryDate()) : "";
            item.setText(3, dateStr);

            TableEditor editor = new TableEditor(table);
            Composite comp = new Composite(table, SWT.NONE);
            comp.setLayout(new RowLayout());

            Button modifyBtn = new Button(comp, SWT.PUSH);
            modifyBtn.setText("Modify");
            modifyBtn.addListener(SWT.Selection, e -> openProductForm(parent, product));

            Button deleteBtn = new Button(comp, SWT.PUSH);
            deleteBtn.setText("Delete");
            deleteBtn.addListener(SWT.Selection, e -> confirmDelete(parent, product));
            editor.grabHorizontal = true;
            editor.setEditor(comp, item, 4);
            editors.add(comp); // track created editors
        }
    }

    private void openProductForm(Shell parent, Products existingProduct) {
        Shell form = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        form.setText(existingProduct == null ? "New Product" : "Modify Product");
        form.setSize(300, 200);
        form.setLayout(new GridLayout(2, false));

        new Label(form, SWT.NONE).setText("Name:");
        Text nameText = new Text(form, SWT.BORDER);
        nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(form, SWT.NONE).setText("Price:");
        Text priceText = new Text(form, SWT.BORDER);
        priceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        new Label(form, SWT.NONE).setText("Expiry Date:");
        DateTime datePicker = new DateTime(form, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        datePicker.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        if (existingProduct != null) {
            nameText.setText(existingProduct.getName());
            priceText.setText(String.valueOf(existingProduct.getProductInventory().getPrice()));
            if (existingProduct.getProductInventory().getExpiryDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(existingProduct.getProductInventory().getExpiryDate());
                datePicker.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }
        }

        Button saveBtn = new Button(form, SWT.PUSH);
        saveBtn.setText("Save");
        saveBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        saveBtn.addListener(SWT.Selection, e -> {
            try {
                String name = nameText.getText();
                double price = Double.parseDouble(priceText.getText());
                Calendar cal = Calendar.getInstance();
                cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDay());
                Date expiry = cal.getTime();
                if (name.isEmpty()) {
                    MessageBox mb = new MessageBox(form, SWT.ICON_ERROR);
                    mb.setMessage("Name cannot be empty");
                    mb.open();
                    return;
                }
                if (existingProduct == null) {
                	Products newProduct = new Products();
                	newProduct.setName(name);
                	newProduct.getProductInventory().setPrice(price);
                	newProduct.getProductInventory().setExpiryDate(expiry);
                	this.postProducts.executeSync(newProduct).get();
                	//System.out.println("Added new product: " + name);
                } else {
                    existingProduct.setName(name);
                    existingProduct.getProductInventory().setPrice(price);
                    existingProduct.getProductInventory().setExpiryDate(expiry);
                    this.putProducts.execute(existingProduct.getId(), existingProduct).get();
                    //System.out.println("Modified product: " + name);
                }
                refreshTable(parent);
                form.close();
            } catch (NumberFormatException ex) {
                MessageBox error = new MessageBox(form, SWT.ICON_ERROR);
                error.setMessage("Invalid price format.");
                error.open();
            } catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        form.open();
    }

    private void confirmDelete(Shell parent, Products product) {
        MessageBox confirm = new MessageBox(parent, SWT.ICON_WARNING | SWT.YES | SWT.NO);
        confirm.setText("Confirm Deletion");
        confirm.setMessage("Are you sure you want to delete " + product.getName() + "?");
        int response = confirm.open();
        if (response == SWT.YES) {
        	this.deleteProducts.execute(product.getId());
            refreshTable(parent);
        }
    }
}
