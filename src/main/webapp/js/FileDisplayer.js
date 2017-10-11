const FileDisplayer = class {
  constructor(url) {
    this.url = url;
    this.buildFileList = this.buildFileList.bind(this);
  }

  displayIn(elementId) {
      JsonProvider
          .getJson(this.url)
          .then(this.buildFileList)
          .then(fileList => {
              const parent = document.getElementById(elementId);
              const oldList = document.getElementById('file-list');
              if (!oldList) {
                  parent.appendChild(fileList);
              } else {
                  parent.replaceChild(fileList, oldList);
              }
          });
  }

  buildFileList(files) {
      const table = this.buildTable();
      table.appendChild(this.buildTableHeader('Files Moved'));
      table.appendChild(this.buildTableBody(files));
      return table;
  }

  buildTable() {
      const fileList = document.createElement('table');
      fileList.id = 'file-list';
      fileList.className = 'table table-responsive table-striped table-hover';
      return fileList;
  }

  buildTableHeader(headerText) {
    const thead = document.createElement('thead');
    thead.innerHTML =
        `<tr class='bg-info'>
            <th><h3>${headerText}</h3></th>
        </tr>`;
    return thead;
  }

  buildTableBody(files) {
      return files
          .map(this.buildFileElement, this)
          .reduceRight(
              (fileList, el) => {
                  fileList.appendChild(el);
                  return fileList;
              },
              document.createElement('tbody')
          );
  }

  buildFileElement(file) {
      const row = document.createElement('tr');
      row.appendChild(this.buildFileColumnElement(file));
      return row;
  }

  buildFileColumnElement(file) {
      const col = document.createElement('td');
      col.innerHTML = file;
      return col;
  }
};
