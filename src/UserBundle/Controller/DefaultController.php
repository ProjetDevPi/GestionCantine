<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{

    public function indexbackAction()
    {
        return $this->render('backAdmin.html.twig');
    }
}
