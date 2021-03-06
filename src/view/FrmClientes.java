package view;

import bll.ClienteBLL;
import bll.ValidacoesPessoasBLL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Endereco;
import model.Cliente;
import model.Contato;

public class FrmClientes extends javax.swing.JFrame {

    private static FrmClientes telaClientesGeral = null;

    DefaultTableModel modelo = new DefaultTableModel();
    ClienteBLL clienteBll = new ClienteBLL();
    ValidacoesPessoasBLL validacoesclientes = new ClienteBLL();
    Cliente cliente = new Cliente();

    Vector<Endereco> vetorEnderecos;
    Vector<Contato> vetorContatos;

    public static FrmClientes getTelaCliente() {
        if (telaClientesGeral == null) {
            telaClientesGeral = new FrmClientes();
        }
        return telaClientesGeral;
    }

    private FrmClientes() {
        criarTabela();
        consultar();
        initComponents();
        preencherCbxs();
    }

    private Date CriarNovaData(String data) {
        if (data == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        java.sql.Date a = null;
        try {
            a = new java.sql.Date(format.parse(data).getTime());
        } catch (ParseException e) {
        }
        return a;
    }

    private String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formatter.format(dtConsulta);
        } catch (Exception e) {
            return null;
        }
    }

    private void criarTabela() {
        tblPessoas = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Email");
        modelo.addColumn("Data nascimento");
        modelo.addColumn("Endereço");
        modelo.addColumn("Contato");

        tblPessoas.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblPessoas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblPessoas.getColumnModel().getColumn(6).setPreferredWidth(50);
    }

    private void consultar() {
        modelo.setNumRows(0);
        List<Cliente> lista = new ArrayList<Cliente>();

        lista = clienteBll.consultar();

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[]{
                    lista.get(i).getCodigo(),
                    lista.get(i).getNome(),
                    lista.get(i).getCpf(),
                    lista.get(i).getEmail(),
                    lista.get(i).getDataNascimento(),
                    lista.get(i).getIdEndereco().getBairro(),
                    lista.get(i).getIdContato().getNumero()
                });
            }
        } else {
            modelo.setNumRows(0);
        }
    }

    private void preencheCampos(int id) {
        cliente = clienteBll.consultaPorId(id);
        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtEmail.setText(cliente.getEmail());
        txtDataNascimento.setText(convertDate(cliente.getDataNascimento()));
        cbxEnderecos.setSelectedItem(cliente.getIdEndereco());
        cbxContatos.setSelectedItem(cliente.getIdContato());
    }

    private void limparCampos() {
        txtNome.setText("");
        cbxEnderecos.setSelectedIndex(0);
        cbxContatos.setSelectedIndex(0);
        txtCpf.setValue("");
        txtEmail.setText("");
        txtDataNascimento.setValue("");
        btnSalvar.setEnabled(true);
    }

    private void preencherCbxs() {
        vetorEnderecos = clienteBll.listarEnderecos();
        cbxEnderecos.setModel(new DefaultComboBoxModel(vetorEnderecos));

        vetorContatos = clienteBll.listarContatos();
        cbxContatos.setModel(new DefaultComboBoxModel(vetorContatos));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxEnderecos = new javax.swing.JComboBox<>();
        btnAdicionarEndereco = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxContatos = new javax.swing.JComboBox<>();
        btnAdicionarContato = new javax.swing.JButton();
        teladeFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de clientes");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome completo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 10, 110, 20);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(30, 30, 260, 28);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" CPF");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 10, 30, 20);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(320, 30, 110, 28);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 60, 30, 20);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(30, 80, 260, 28);

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });
        txtDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyTyped(evt);
            }
        });
        getContentPane().add(txtDataNascimento);
        txtDataNascimento.setBounds(320, 80, 110, 28);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data Nascimento");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(320, 60, 100, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" Endereço");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(460, 10, 70, 20);

        cbxEnderecos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEnderecosItemStateChanged(evt);
            }
        });
        cbxEnderecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxEnderecosMouseClicked(evt);
            }
        });
        getContentPane().add(cbxEnderecos);
        cbxEnderecos.setBounds(460, 30, 240, 28);

        btnAdicionarEndereco.setText("+");
        btnAdicionarEndereco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarEndereco);
        btnAdicionarEndereco.setBounds(700, 30, 41, 28);

        tblPessoas.setModel(modelo);
        tblPessoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPessoasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPessoas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 750, 180);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_salvar.png"))); // NOI18N
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(440, 310, 55, 41);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_excluir.png"))); // NOI18N
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(510, 310, 55, 41);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(580, 310, 55, 41);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icone_limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(650, 310, 55, 41);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Contato");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(460, 60, 50, 20);

        cbxContatos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxContatosItemStateChanged(evt);
            }
        });
        cbxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxContatosActionPerformed(evt);
            }
        });
        getContentPane().add(cbxContatos);
        cbxContatos.setBounds(460, 80, 240, 28);

        btnAdicionarContato.setText("+");
        btnAdicionarContato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarContatoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarContato);
        btnAdicionarContato.setBounds(700, 80, 41, 28);

        teladeFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo_tela.jpg"))); // NOI18N
        getContentPane().add(teladeFundo);
        teladeFundo.setBounds(0, 0, 830, 370);

        setSize(new java.awt.Dimension(775, 387));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtNome.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE LETRAS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCpfKeyTyped

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed

    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtDataNascimentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataNascimentoKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "DIGITE SOMENTE NUMEROS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDataNascimentoKeyTyped

    FrmEnderecos telaEnderecos;
    FrmContatos telaContatos;

    private void btnAdicionarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEnderecoActionPerformed
        if (telaEnderecos == null) {
            telaEnderecos = FrmEnderecos.getTelaEndereco();
            telaEnderecos.setVisible(true);
        } else {
            telaEnderecos.dispose();
            telaEnderecos.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarEnderecoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            cliente.setNome(txtNome.getText());
            cliente.setCpf(txtCpf.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            cliente.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            cliente.setIdContato(vetorContatos.get(cbxContatos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } 
            else if(txtNome.getText().length() < 15){
                JOptionPane.showMessageDialog(rootPane, "DIGITE SEU NOME COMPLETO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
            else {

                if (validacoesclientes.validacoes(txtEmail.getText(), txtDataNascimento.getText(), txtCpf.getText())
                        && !clienteBll.verificarCPFsIguais(txtCpf.getText())) {
                    if (clienteBll.salvar(cliente)) {
                        JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    if (clienteBll.verificarCPFsIguais(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF JÁ FOI CADASTRADO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!clienteBll.isEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "EMAIL INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!clienteBll.isCpf(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!clienteBll.isData(txtDataNascimento.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA NASCIMENTO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO SALVAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (clienteBll.remover(clienteBll.consultaPorId(cliente.getCodigo()))) {
                    JOptionPane.showMessageDialog(rootPane, "Removido com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao remover!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO REMOVER!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
        consultar();
        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            cliente.setNome(txtNome.getText());
            cliente.setCpf(txtCpf.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setDataNascimento(CriarNovaData(txtDataNascimento.getText()));
            cliente.setIdEndereco(vetorEnderecos.get(cbxEnderecos.getSelectedIndex()));
            cliente.setIdContato(vetorContatos.get(cbxContatos.getSelectedIndex()));

            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtEmail.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "CAMPO EM BRANCO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
            else if(txtNome.getText().length() < 15){
                JOptionPane.showMessageDialog(rootPane, "DIGITE SEU NOME COMPLETO!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
            else {
                if (validacoesclientes.validacoes(txtEmail.getText(), txtDataNascimento.getText(), txtCpf.getText())) {
                    if (clienteBll.editar(cliente)) {
                        JOptionPane.showMessageDialog(rootPane, "Editado com sucesso!", "Mensagem!!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao editar!", "Mensagem!!!", JOptionPane.WARNING_MESSAGE);
                    }
                    consultar();
                    limparCampos();
                } else {
                    if (!clienteBll.isEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "EMAIL INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!clienteBll.isCpf(txtCpf.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "CPF INVALIDO!", "Cuidado!", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!clienteBll.isData(txtDataNascimento.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "DATA NASCIMENTO INVALIDO!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERRO AO EDITAR!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        preencherCbxs();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPessoasMouseClicked
        btnSalvar.setEnabled(false);
        int linha = tblPessoas.getSelectedRow();
        Integer codigo = (Integer) tblPessoas.getValueAt(linha, 0);
        preencheCampos((int) codigo);
    }//GEN-LAST:event_tblPessoasMouseClicked

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        Character ch = evt.getKeyChar();
        int comprimentoDeCampo = txtEmail.getText().length();
        if (comprimentoDeCampo >= 40) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "LIMITE DE 40 DIGITOS!", "Atenção!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtEmailKeyTyped

    private void btnAdicionarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarContatoActionPerformed
        if (telaContatos == null) {
            telaContatos = FrmContatos.getTelaContato();
            telaContatos.setVisible(true);
        } else {
            telaContatos.dispose();
            telaContatos.setVisible(true);
        }
    }//GEN-LAST:event_btnAdicionarContatoActionPerformed

    private void cbxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxContatosActionPerformed
    }//GEN-LAST:event_cbxContatosActionPerformed

    private void cbxEnderecosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxEnderecosMouseClicked
    }//GEN-LAST:event_cbxEnderecosMouseClicked

    private void cbxEnderecosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEnderecosItemStateChanged
    }//GEN-LAST:event_cbxEnderecosItemStateChanged

    private void cbxContatosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxContatosItemStateChanged
    }//GEN-LAST:event_cbxContatosItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarContato;
    private javax.swing.JButton btnAdicionarEndereco;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxContatos;
    private javax.swing.JComboBox<String> cbxEnderecos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JLabel teladeFundo;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
