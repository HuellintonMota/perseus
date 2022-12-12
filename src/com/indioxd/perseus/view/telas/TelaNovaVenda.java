/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.indioxd.perseus.view.telas;

import com.indioxd.perseus.core.entity.PessoaEntity;
import com.indioxd.perseus.core.entity.ProdutoEntity;
import com.indioxd.perseus.core.entity.VendaEntity;
import com.indioxd.perseus.core.service.ClienteService;
import com.indioxd.perseus.core.service.ProdutoService;
import com.indioxd.perseus.core.service.VendaService;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author heto1
 */
public class TelaNovaVenda extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroUsuario
     */
    public TelaNovaVenda() {
        initComponents();
        //listarUsuarios();
    }
    
    public TelaNovaVenda(VendaEntity venda) {
        initComponents();
        this.venda = venda;
        carregarVenda();
        //listarUsuarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        lblCliente = new javax.swing.JLabel();
        txtIDCliente = new javax.swing.JTextField();
        lblProduto = new javax.swing.JLabel();
        txtProduto = new javax.swing.JTextField();
        lblQtdade = new javax.swing.JLabel();
        txtQtdade = new javax.swing.JSpinner();
        btnAdicionar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNomeCliente = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Venda");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(180, 105, 255));
        lblID.setText("ID");

        txtID.setEnabled(false);

        txtData.setEnabled(false);

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(180, 105, 255));
        lblData.setText("Data");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Quantidade", "Valor Unitário", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblProdutos);

        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(180, 105, 255));
        lblCliente.setText("Cliente");

        txtIDCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDClienteFocusLost(evt);
            }
        });

        lblProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblProduto.setForeground(new java.awt.Color(180, 105, 255));
        lblProduto.setText("Produto");

        txtProduto.setToolTipText("Id do Produto");
        txtProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProdutoFocusLost(evt);
            }
        });

        lblQtdade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQtdade.setForeground(new java.awt.Color(180, 105, 255));
        lblQtdade.setText("Quantidade");

        txtQtdade.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));

        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(151, 74, 160));
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/indioxd/perseus/img/add_dim.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setIconTextGap(6);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(204, 0, 0));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/indioxd/perseus/img/cross_dim.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setIconTextGap(6);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(151, 74, 160));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/indioxd/perseus/img/check-mark_dim.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setForeground(new java.awt.Color(151, 74, 160));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/indioxd/perseus/img/cancel_dim.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNomeCliente.setEditable(false);

        txtDescricao.setEditable(false);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(180, 105, 255));
        lblTotal.setText("Total R$ 0,00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblProduto)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDescricao))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblID)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNomeCliente)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblData)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblQtdade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtQtdade, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTotal)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar)))
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProduto)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtQtdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQtdade)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        txtProduto.getAccessibleContext().setAccessibleName("ID Produto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(txtProduto);

        setSize(new java.awt.Dimension(681, 499));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private VendaEntity venda;
    private PessoaEntity cliente;
    private ProdutoEntity produto;
    private Double total = 0.0;
    private List<ProdutoEntity> itens = new ArrayList<ProdutoEntity>();
    private final NumberFormat nf = NumberFormat.getInstance();
    private final NumberFormat cf = NumberFormat.getCurrencyInstance();
    
    private void limpar(){
       
        produto = null;
        total = 0.0;
        venda = null;
        cliente = null;
        itens = new ArrayList<ProdutoEntity>();
        carregaItens();
        limparProduto();
        txtIDCliente.setText("");
        txtNomeCliente.setText("");
    }
    
    private void limparProduto(){
        produto = null;
        txtProduto.setText("");
        txtDescricao.setText("");
        txtQtdade.setValue(1);
    }
    
    private void carregarVenda(){
        
        
    }
    
    
    private void carregaItens(){
        DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
        model.getDataVector().removeAllElements();
        total = 0.0;
        for (ProdutoEntity pro : itens) {
            total += pro.getPrecoCusto();
            model.addRow(new Object[]{pro.getCodigo(),pro.getDescricao(), nf.format(pro.getEstoque()), cf.format(pro.getPrecoVenda()),cf.format(pro.getPrecoCusto())});
        }
        
        lblTotal.setText("Total "+ cf.format(total));
    }
    
    
    
    public void carregarVendaPorID(Long codigoVenda){
        VendaEntity ven;
        try {
            ven = new VendaService().buscarVendaporId(codigoVenda);
            txtID.setText(ven.getCodigo().toString());
            txtIDCliente.setText(ven.getCliente().getCodigo().toString());
            txtNomeCliente.setText(ven.getCliente().getFantasia()+" - " + ven.getCliente().getCpfCnpj());
            txtData.setText(ven.getDataVenda().toString());
            
            this.itens = ven.getProdutosVendidos();
            this.venda = ven;
            
            carregaItens();
            
        } catch (NegocioException ex) {
            Logger.getLogger(TelaNovaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
    
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        VendaEntity v = new VendaEntity();
        VendaService vs = new VendaService();
        
        if(txtID.getText().equals("")){
            v.setCliente(cliente);
            v.setProdutosVendidos(itens);
            v.setTotal(total);

            try {
                JOptionPane.showMessageDialog(rootPane, vs.salvarVenda(v));
                limpar();
            } catch (NegocioException ex) {
                Logger.getLogger(TelaNovaVenda.class.getName()).log(Level.SEVERE, null, ex);
                ex.ExibeErro();
            }
        }else{
            this.dispose();
            
            
        }
            limpar();
            //listarUsuarios();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        
        
        dispose();
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        TelaVenda tv = new TelaVenda();
        tv.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void txtIDClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDClienteFocusLost
        try {
            // TODO add your handling code here:
            if(!txtIDCliente.getText().equals("")){
                PessoaEntity cli = new ClienteService().buscarClientePorID(Long.parseLong(txtIDCliente.getText()));
                if(cli != null){
                    txtNomeCliente.setText(cli.getFantasia()+" - "+cli.getCpfCnpj());
                    this.cliente = cli;
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(TelaNovaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_txtIDClienteFocusLost

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        ProdutoEntity p = produto;
        
        p.setEstoque(Double.parseDouble(txtQtdade.getValue().toString()));
        p.setPrecoCusto(p.getPrecoVenda()*p.getEstoque());
        
        itens.add(p);
        
        carregaItens();
        
        limparProduto();

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProdutoFocusLost
        try {
            // TODO add your handling code here:
            if(!txtProduto.getText().equals("")){
                ProdutoEntity prod = new ProdutoService().buscarProdutoPorID(Long.parseLong(txtProduto.getText()));
                if(prod!= null){
                    txtDescricao.setText(prod.getDescricao() + " - "+ cf.format(prod.getPrecoVenda()));
                    this.produto = prod;
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(TelaNovaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_txtProdutoFocusLost

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        
        itens.remove(tblProdutos.getSelectedRow());
        
        carregaItens();
        
        limparProduto();
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaNovaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblQtdade;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JSpinner txtQtdade;
    // End of variables declaration//GEN-END:variables
}
