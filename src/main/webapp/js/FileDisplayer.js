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
      return files
          .map(this.buildFileElement)
          .reduceRight(
              (fileList, el) => {
                fileList.appendChild(el);
                return fileList;
              },
              this.buildFileListElement()
          );
  }

  buildFileElement(file) {
      const div = document.createElement('div');
      div.innerHTML = file;
      return div;
  }

  buildFileListElement() {
      const fileList = document.createElement('div');
      fileList.id = 'file-list';
      return fileList;
  }
};
