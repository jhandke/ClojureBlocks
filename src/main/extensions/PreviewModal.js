import { Modal } from "@blockly/plugin-modal";

export default class PreviewModal extends Modal {
    constructor(title, workspace) {
        super(title, workspace);
    }

    init(content) {
        this.rendercontent(content);
    }

    rendercontent(content) {
        /*
         * Creates the Modal. The generated modal looks like:
         * <div class="blocklyModalContainer" role="dialog">
         *   <header class="blocklyModalHeader">
         *     <h2 class="blocklyModalHeaderTitle">Modal Name</h2>
         *     <button class="blocklyModalBtn blocklyModalBtnClose">X</button>
         *   </header>
         *   <div class="blocklyModalContent">
         *   </div>
         *   <div class="blocklyModalFooter">
         *   </div>
         * </div>
         */
    
        // Create Overlay
        this.htmlDiv_ = document.createElement('div');
        this.htmlDiv_.className = 'blocklyModalOverlay';
        // End Creating the Overlay
    
        // Create Container
        const modalContainer = document.createElement('div');
        modalContainer.className = 'blocklyModalContainer';
        modalContainer.setAttribute('role', 'dialog');
        modalContainer.setAttribute('aria-labelledby', this.title_);
        // End creating the container
    
        // Add Events
        this.addEvent_(modalContainer, 'keydown',
            this, this.handleKeyDown_);
    
        if (this.shouldCloseOnOverlayClick) {
          this.addEvent_(this.htmlDiv_, 'click', this, this.hide);
          this.addEvent_(modalContainer, 'click', this, (e) => {
            e.stopPropagation();
          });
        }
    
        // Create the header
        const modalHeader = document.createElement('header');
        modalHeader.className = 'blocklyModalHeader';
    
        this.renderHeader_(modalHeader);
    
        const exitButton = document.createElement('button');
        exitButton.className = 'blocklyModalBtn blocklyModalBtnClose';
        this.addEvent_(exitButton, 'click', this, this.onCancel_);
        modalHeader.appendChild(exitButton);
        // End create header
    
        // Create content
        const modalContent = document.createElement('div');
        modalContent.className = 'blocklyModalContent';
        modalContent.innerText = content;
        this.renderContent_(modalContent);
        // End creating content
    
        // Create Footer
        const modalFooter = document.createElement('footer');
        modalFooter.className = 'blocklyModalFooter';
    
        this.renderFooter_(modalFooter);
        // End creating footer
    
        modalContainer.appendChild(modalHeader);
        modalContainer.appendChild(modalContent);
        modalContainer.appendChild(modalFooter);
        this.htmlDiv_.appendChild(modalContainer);
      }
}