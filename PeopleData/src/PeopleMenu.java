import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PeopleMenu {
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> prodiComboBox;
    private JButton tambahButton;
    private JButton hapusButton;
    private JButton bersihkanButton;
    private JButton ubahButton;
    private JTable personTable;
    private DefaultTableModel tableModel;

    private final ArrayList<Person> daftarPerson = new ArrayList<>();

    public PeopleMenu() {
        isiPilihanComboBox();
        initTable();
        isiDataAwal();
        pasangAksiTombol();
    }

    private void isiPilihanComboBox() {
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{"Pilih Kategori", "Mahasiswa Aktif", "Mahasiswa Non Aktif", "Alumni"}
        ));

        prodiComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "Pilih Program Studi",
                        "Ilmu Komputer",
                        "Pendidikan Ilmu Komputer",
                        "Matematika",
                        "Pendidikan Matematika",
                        "Kimia",
                        "Pendidikan Kimia",
                        "Fisika",
                        "Pendidikan Fisika",
                        "Biologi",
                        "Pendidikan Biologi",
                        "IPSE",
                        "Lainnya"
                }
        ));
    }

    private void initTable() {
        String[] columnNames = {
                "NIM", "Nama Mahasiswa", "Tahun Lahir", "Kategori", "Program Studi"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        personTable.setModel(tableModel);
        personTable.setRowHeight(25);
        personTable.getTableHeader().setReorderingAllowed(false);
        personTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        personTable.setShowGrid(true);
        personTable.setGridColor(new Color(225, 225, 225));
        personTable.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        personTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        personTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        personTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        personTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        personTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        personTable.getColumnModel().getColumn(3).setPreferredWidth(130);
        personTable.getColumnModel().getColumn(4).setPreferredWidth(180);
    }

    private void isiDataAwal() {
        daftarPerson.add(new Person("2507925", "Keysha Ega Magani", 2006,
                "Mahasiswa", "Ilmu Komputer"));
        daftarPerson.add(new Person("2511173", "Najwa Angelita Rahma Haris", 2005,
                "Mahasiswa", "Ilmu Komputer"));
        daftarPerson.add(new Person("2524321", "Salwa nurhabibah", 2004,
                "Mahasiswa", "Biologi"));
        daftarPerson.add(new Person("2507111", "Kemal al dzaki", 2006,
                "Mahasiswa", "Matematika"));
        daftarPerson.add(new Person("2507929", "Fathir al fath", 2005,
                "Mahasiswa", "Kimia"));

        tampilkanTabel();
    }

    private void pasangAksiTombol() {
        tambahButton.addActionListener(e -> tambahData());
        ubahButton.addActionListener(e -> ubahData());
        hapusButton.addActionListener(e -> hapusData());
        bersihkanButton.addActionListener(e -> bersihkanForm());

        personTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                tampilkanDataTerpilih();
            }
        });
    }

    private boolean validasiInput() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String tahun = tahunLahirField.getText().trim();

        if (id.isEmpty() || nama.isEmpty() || tahun.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "NIM, Nama Mahasiswa, dan Tahun Lahir wajib diisi.",
                    "Data Belum Lengkap", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int tahunLahir = Integer.parseInt(tahun);
            if (tahunLahir < 1900 || tahunLahir > 2026) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Tahun lahir harus berupa tahun antara 1900 dan 2026.",
                        "Tahun Lahir Tidak Valid", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Tahun lahir harus diisi dengan angka.",
                    "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (kategoriComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Silakan pilih kategori.", "Kategori Belum Dipilih",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (prodiComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Silakan pilih program studi.", "Program Studi Belum Dipilih",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void tambahData() {
        if (!validasiInput()) {
            return;
        }

        String id = idField.getText().trim();
        for (Person person : daftarPerson) {
            if (person.getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(mainPanel,
                        "NIM tersebut sudah terdaftar.", "NIM Duplikat",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Person person = new Person(
                id,
                namaField.getText().trim(),
                Integer.parseInt(tahunLahirField.getText().trim()),
                kategoriComboBox.getSelectedItem().toString(),
                prodiComboBox.getSelectedItem().toString()
        );

        daftarPerson.add(person);
        tampilkanTabel();
        bersihkanForm();
        JOptionPane.showMessageDialog(mainPanel, "Data mahasiswa berhasil ditambahkan.");
    }

    private void ubahData() {
        int baris = personTable.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Pilih baris data pada tabel yang ingin diubah.",
                    "Data Belum Dipilih", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!validasiInput()) {
            return;
        }

        // NIM dipakai sebagai identitas data dan tidak diubah ketika proses update.
        Person person = daftarPerson.get(baris);
        person.setNama(namaField.getText().trim());
        person.setTahunLahir(Integer.parseInt(tahunLahirField.getText().trim()));
        person.setKategori(kategoriComboBox.getSelectedItem().toString());
        person.setProgramStudi(prodiComboBox.getSelectedItem().toString());

        tampilkanTabel();
        bersihkanForm();
        JOptionPane.showMessageDialog(mainPanel, "Data mahasiswa berhasil diubah.");
    }

    private void hapusData() {
        int baris = personTable.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Pilih baris data pada tabel yang ingin dihapus.",
                    "Data Belum Dipilih", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Person person = daftarPerson.get(baris);
        int konfirmasi = JOptionPane.showConfirmDialog(
                mainPanel,
                "Hapus data mahasiswa " + person.getNama() + " (" + person.getId() + ")?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            daftarPerson.remove(baris);
            tampilkanTabel();
            bersihkanForm();
            JOptionPane.showMessageDialog(mainPanel, "Data mahasiswa berhasil dihapus.");
        }
    }

    private void tampilkanTabel() {
        tableModel.setRowCount(0);

        for (Person person : daftarPerson) {
            tableModel.addRow(new Object[]{
                    person.getId(),
                    person.getNama(),
                    person.getTahunLahir(),
                    person.getKategori(),
                    person.getProgramStudi()
            });
        }
    }

    private void tampilkanDataTerpilih() {
        int baris = personTable.getSelectedRow();
        if (baris != -1) {
            Person person = daftarPerson.get(baris);
            idField.setText(person.getId());
            namaField.setText(person.getNama());
            tahunLahirField.setText(String.valueOf(person.getTahunLahir()));
            kategoriComboBox.setSelectedItem(person.getKategori());
            prodiComboBox.setSelectedItem(person.getProgramStudi());
            idField.setEditable(false);
        }
    }

    private void bersihkanForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        prodiComboBox.setSelectedIndex(0);
        idField.setEditable(true);
        personTable.clearSelection();
        idField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PeopleMenu menu = new PeopleMenu();
            JFrame frame = new JFrame("Student Data Management");
            frame.setContentPane(menu.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(850, 580));
            frame.pack();
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(15, 15, 15, 15), 10, 8));
        final JLabel label1 = new JLabel();
        label1.setText("STUDENT DATA MANAGEMENT");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("NIM");
        mainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idField = new JTextField();
        mainPanel.add(idField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(220, -1), null, 0, false));
        tambahButton = new JButton();
        tambahButton.setText("Tambah");
        mainPanel.add(tambahButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Nama Mahasiswa");
        mainPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        namaField = new JTextField();
        mainPanel.add(namaField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(220, -1), null, 0, false));
        ubahButton = new JButton();
        ubahButton.setText("Ubah");
        mainPanel.add(ubahButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Tahun Lahir");
        mainPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tahunLahirField = new JTextField();
        mainPanel.add(tahunLahirField, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(220, -1), null, 0, false));
        hapusButton = new JButton();
        hapusButton.setText("Hapus");
        mainPanel.add(hapusButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Kategori");
        mainPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        kategoriComboBox = new JComboBox();
        mainPanel.add(kategoriComboBox, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(220, -1), null, 0, false));
        bersihkanButton = new JButton();
        bersihkanButton.setText("Bersihkan");
        mainPanel.add(bersihkanButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Program Studi");
        mainPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        prodiComboBox = new JComboBox();
        mainPanel.add(prodiComboBox, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(220, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 220), null, 0, false));
        personTable = new JTable();
        scrollPane1.setViewportView(personTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
