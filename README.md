# TP4DPBO2526C1

## Janji

Saya Keysha Ega Magani dengan NIM 2507925 mengerjakan tugas ini dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## 🎓 PeopleData — Student Data Management

**PeopleData** merupakan aplikasi desktop sederhana untuk mengelola data mahasiswa yang dibuat menggunakan **Java Swing** sebagai antarmuka grafis (*Graphical User Interface*).

Program ini dibuat untuk memenuhi tugas praktikum mata kuliah Desain dan Pemrograman Berorientasi Objek.

Aplikasi ini memungkinkan pengguna untuk menambahkan, melihat, mengubah, dan menghapus data mahasiswa melalui antarmuka yang interaktif. Data mahasiswa ditampilkan dalam bentuk tabel agar lebih mudah dibaca dan dikelola.

Program menerapkan konsep **Object-Oriented Programming (OOP)** serta menggunakan `ArrayList` untuk menyimpan dan mengelola data mahasiswa selama aplikasi berjalan.

---

## Fitur Program

Program memiliki beberapa fitur utama untuk mengelola data mahasiswa.

### Data Awal

Program menyediakan 5 data mahasiswa awal yang dapat digunakan untuk mencoba fitur-fitur aplikasi.

Data mahasiswa meliputi:

- NIM
- Nama
- Tahun Lahir
- Kategori
- Program Studi

Kategori mahasiswa yang tersedia:

- Mahasiswa Aktif
- Mahasiswa Nonaktif
- Alumni

### 1. Tampilkan Data Mahasiswa

Menampilkan seluruh data mahasiswa dalam bentuk tabel pada antarmuka aplikasi.

Informasi yang ditampilkan meliputi:

- NIM
- Nama
- Tahun Lahir
- Kategori
- Program Studi

Tabel digunakan untuk memudahkan pengguna melihat dan memilih data mahasiswa yang akan dikelola.

### 2. Tambah Data Mahasiswa

Pengguna dapat menambahkan data mahasiswa baru melalui form yang tersedia.

Pengguna mengisi NIM, nama, dan tahun lahir, kemudian memilih kategori mahasiswa serta program studi melalui komponen pilihan.

Data yang valid akan ditambahkan ke dalam daftar mahasiswa dan ditampilkan pada tabel.

### 3. Ubah Data Mahasiswa

Pengguna dapat memilih data mahasiswa pada tabel untuk mengubah informasi yang diperlukan.

Setelah perubahan disimpan, data mahasiswa pada tabel akan diperbarui.

### 4. Hapus Data Mahasiswa

Pengguna dapat menghapus data mahasiswa yang dipilih dari tabel.

Sebelum data dihapus, program menampilkan dialog konfirmasi untuk memastikan bahwa pengguna benar-benar ingin menghapus data tersebut.

### 5. Bersihkan Form

Pengguna dapat mengosongkan kembali form input agar dapat memasukkan data baru atau membatalkan pengisian data yang sedang dilakukan.

### 6. Pilihan Kategori dan Program Studi

Program menggunakan komponen `JComboBox` untuk menyediakan pilihan kategori mahasiswa dan program studi.

Penggunaan komponen pilihan membantu pengguna memilih nilai yang tersedia sehingga input menjadi lebih konsisten.

---

## Konsep OOP

### Class `Person`

Class `Person` digunakan untuk merepresentasikan data seseorang atau mahasiswa yang dikelola oleh aplikasi.

Class ini menyimpan atribut yang diperlukan untuk membentuk sebuah objek data mahasiswa.

Atribut yang digunakan:

- `id` atau NIM
- `nama`
- `tahunLahir`
- `kategori`
- `prodi`

Class ini berfungsi sebagai representasi data yang akan ditambahkan, ditampilkan, diubah, dan dihapus melalui aplikasi.

### Class `PeopleMenu`

Class `PeopleMenu` digunakan untuk membangun antarmuka grafis dan menangani interaksi pengguna dengan aplikasi.

Tanggung jawab class ini meliputi:

- Menampilkan form input data mahasiswa.
- Menampilkan data mahasiswa dalam tabel.
- Menangani tombol tambah, ubah, hapus, dan bersihkan.
- Memproses interaksi pengguna.
- Memperbarui tampilan tabel setelah data berubah.
- Menampilkan pesan validasi dan dialog konfirmasi.


## Error Handling

Program perlu melakukan validasi terhadap input pengguna agar data yang dimasukkan sesuai dengan ketentuan.

Validasi yang diterapkan meliputi:

- NIM tidak boleh kosong.
- NIM tidak boleh duplikat ketika menambahkan data baru.
- Nama tidak boleh kosong.
- Tahun lahir harus berupa angka yang valid.
- Kategori harus dipilih dari pilihan yang tersedia.
- Program studi harus dipilih dari pilihan yang tersedia.
- Data harus dipilih sebelum melakukan perubahan atau penghapusan.
- Penghapusan data harus melalui dialog konfirmasi.

Jika input tidak sesuai atau operasi tidak dapat dilakukan, program menampilkan pesan kepada pengguna agar kesalahan dapat diperbaiki.

---

## Struktur Folder

Struktur folder project secara umum:

```text
PeopleData/
│
├── src/
│   ├── Person.java
│   └── PeopleMenu.java
│
├── .gitignore
├── README.md
└── PeopleData.iml
```

> Struktur di atas merupakan gambaran umum. Sesuaikan dengan struktur folder aktual project yang diunggah ke GitHub. File konfigurasi IDE seperti `PeopleData.iml` tidak wajib dimasukkan ke repository.

---

## Dokumentasi

Video dokumentasi aplikasi PeopleData dapat ditonton melalui tautan berikut:

**[Tonton Video Demo PeopleData di YouTube](https://youtu.be/f25umgjksZY)**

Video demo memperlihatkan tampilan aplikasi serta pengujian fitur tambah, ubah, hapus, dan bersihkan form.

---
---

## Repository

Source code aplikasi PeopleData tersedia pada repository GitHub berikut:

**[PeopleData — GitHub](TEMPEL_LINK_REPOSITORY_DI_SINI)**
